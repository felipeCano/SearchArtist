package com.search.artist.data.repository.dataSourceImpl

import com.search.artist.data.api.ArtistAPIService
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.repository.dataSource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val artistAPIService: ArtistAPIService
): ArtistRemoteDataSource {

    override suspend fun searchArtist(
        query: String,
        page: Int,
        perPage: Int
    ): Response<SearchArtistResponse> {
        return artistAPIService.searchArtist(query,page,perPage)
    }

    override suspend fun getArtistDetail(id: Int): Response<ArtistDetailResponse> {
        return artistAPIService.getArtistDetail(id)
    }

    override suspend fun getArtistReleases(
        id: Int,
        page: Int,
        perPage: Int,
        sort: String,
        sortOrder: String
    ): Response<ArtistReleasesResponse> {
        return artistAPIService.getArtistReleases(id,sort,sortOrder,page,perPage)
    }


}