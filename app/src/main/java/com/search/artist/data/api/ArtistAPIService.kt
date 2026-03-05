package com.search.artist.data.api

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistAPIService {

    @GET("database/search")
    suspend fun searchArtist(
        @Query("release_title") releaseTitle: String?,
        @Query("artist") artistName: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchArtistResponse>

    @GET("artists/{artist_id}")
    suspend fun getArtistDetail(
        @Path("artist_id") artistId: Int
    ): Response<ArtistDetailResponse>

    @GET("artists/{artist_id}/releases")
    suspend fun getArtistReleases(
        @Path("artist_id") artistId: Int,
        @Query("sort") sort: String,
        @Query("sort_order") sortOrder: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ArtistReleasesResponse>
}