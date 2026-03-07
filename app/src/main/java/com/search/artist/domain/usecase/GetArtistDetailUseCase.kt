package com.search.artist.domain.usecase

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository

/**
 * Use case responsible for retrieving the detailed information of a specific artist.
 * Acts as an intermediary between the presentation layer and the data repository.
 * * @property artistRepository The repository implementation to fetch artist data.
 */
class GetArtistDetailUseCase(private val artistRepository: ArtistRepository) {

    /**
     * Executes the business logic to fetch artist details.
     * * @param id The unique identifier of the artist to retrieve.
     * @return A [Resource] containing the [ArtistDetailResponse] data or an error state.
     */
    suspend fun execute(
        id: Int
    ): Resource<ArtistDetailResponse> {
        return artistRepository.getArtistDetail(id)
    }
}