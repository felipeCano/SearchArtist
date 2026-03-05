package com.search.artist.data.repository.dataSource

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun searchArtist(
        query: String,
        page: Int,
        perPage: Int = 30
    ): Response<SearchArtistResponse>

    suspend fun getArtistDetail(id: Int): Response<ArtistDetailResponse>

    suspend fun getArtistReleases(
        id: Int,
        page: Int,
        perPage: Int = 30,
        sort: String = "year",
        sortOrder: String = "desc"
    ): Response<ArtistReleasesResponse>
}