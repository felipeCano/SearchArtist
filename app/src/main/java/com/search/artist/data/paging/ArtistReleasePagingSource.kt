package com.search.artist.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.search.artist.data.model.artistiReleases.Release
import com.search.artist.data.util.Resource
import com.search.artist.domain.usecase.GetArtistReleasesUseCase

class ArtistReleasePagingSource(
    private val getArtistReleasesUseCase: GetArtistReleasesUseCase,
    private val artistId: Int
) : PagingSource<Int, Release>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Release> {
        val page = params.key ?: 1
        return try {
            val resource = getArtistReleasesUseCase.execute(artistId, page, params.loadSize)

            if (resource is Resource.Success && resource.data != null) {
                val data = resource.data.releases.distinctBy { it.id }

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(resource.message ?: "Unknown Error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Release>): Int? = null
}