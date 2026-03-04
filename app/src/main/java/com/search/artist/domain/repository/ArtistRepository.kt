package com.search.artist.domain.repository

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.util.Resource

interface ArtistRepository {

    suspend fun searchArtist(
        query: String,
        page: Int,
        perPage: Int = 30
    ): Resource<SearchArtistResponse>

    suspend fun getArtistDetail(id: Int): Resource<ArtistDetailResponse>
    suspend fun getArtistReleases(
        id: Int,
        page: Int,
        perPage: Int = 30,
        sort: String = "year",
        sortOrder: String = "desc"
    ): Resource<ArtistReleasesResponse>
}