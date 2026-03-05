package com.search.artist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.Result
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.paging.ArtistPagingSource
import com.search.artist.data.repository.dataSource.ArtistRemoteDataSource
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource
) : ArtistRepository {
    override suspend fun searchArtist(
        releaseTitle: String,
        artistName: String,
        page: Int,
        perPage: Int
    ): Resource<SearchArtistResponse> {
        return responseToResource(
            artistRemoteDataSource.searchArtist(
                releaseTitle,artistName,page,perPage
            )
        )
    }

    override suspend fun getArtistDetail(id: Int): Resource<ArtistDetailResponse> {
        return responseToResourceDetail(
            artistRemoteDataSource.getArtistDetail(
                id
            )
        )
    }

    override suspend fun getArtistReleases(
        id: Int,
        page: Int,
        perPage: Int,
        sort: String,
        sortOrder: String
    ): Resource<ArtistReleasesResponse> {
        return responseToResourceRelease(
            artistRemoteDataSource.getArtistReleases(
                id = id,
                page = page,
                perPage = perPage,
                sort = sort,
                sortOrder = sortOrder
            )
        )
    }

    override fun getArtistPagingFlow(query: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { ArtistPagingSource(this, query) }
        ).flow
    }

    private fun responseToResource(response: Response<SearchArtistResponse>): Resource<SearchArtistResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceDetail(response: Response<ArtistDetailResponse>): Resource<ArtistDetailResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceRelease(response: Response<ArtistReleasesResponse>): Resource<ArtistReleasesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}