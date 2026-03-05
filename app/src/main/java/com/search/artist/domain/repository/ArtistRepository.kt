package com.search.artist.domain.repository

import androidx.paging.PagingData
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.util.Resource
import kotlinx.coroutines.flow.Flow
import com.search.artist.data.model.searchArtist.Result

interface ArtistRepository {

    suspend fun searchArtist(
        releaseTitle: String,
        artistName: String,
        page: Int,
        perPage: Int = 30
    ): Resource<SearchArtistResponse>

    suspend fun getArtistDetail(
        id: Int
    ): Resource<ArtistDetailResponse>

    suspend fun getArtistReleases(
        id: Int,
        page: Int,
        perPage: Int = 30,
        sort: String = "year",
        sortOrder: String = "desc"
    ): Resource<ArtistReleasesResponse>

    fun getArtistPagingFlow(query: String): Flow<PagingData<Result>>
}