package com.search.artist.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.search.artist.data.model.searchArtist.Result
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository

class ArtistPagingSource(
    private val repository: ArtistRepository,
    private val query: String
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val resource = repository.searchArtist(
                releaseTitle = "",
                artistName = query,
                page = page,
                perPage = params.loadSize
            )

            if (resource is Resource.Success && resource.data != null) {
                val cleanList = resource.data.results.distinctBy { it.id }

                LoadResult.Page(
                    data = cleanList,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (cleanList.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(resource.message ?: "Unknown Error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}