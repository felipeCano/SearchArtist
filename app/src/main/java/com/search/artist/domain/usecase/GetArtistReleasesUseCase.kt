package com.search.artist.domain.usecase

import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository

class GetArtistReleasesUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute(
        id: Int,
        page: Int,
        perPage: Int = 30,
    ): Resource<ArtistReleasesResponse> {
        return artistRepository.getArtistReleases(id, page, perPage, "year", "desc")
    }
}