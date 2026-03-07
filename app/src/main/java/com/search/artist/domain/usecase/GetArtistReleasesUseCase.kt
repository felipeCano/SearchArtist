package com.search.artist.domain.usecase

import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository
/**
 * Use case responsible for retrieving a paginated list of releases for a specific artist.
 * It interacts with the repository to fetch data sorted by year in descending order.
 */
class GetArtistReleasesUseCase(private val artistRepository: ArtistRepository) {

    /**
     * Executes the business logic to fetch a page of artist releases.
     * * @param id The unique identifier of the artist.
     * @param page The specific page number to retrieve.
     * @param perPage The number of items to return per page (defaults to 30).
     * @return A [Resource] containing the [ArtistReleasesResponse] or an error state.
     */
    suspend fun execute(
        id: Int,
        page: Int,
        perPage: Int = 30,
    ): Resource<ArtistReleasesResponse> {
        return artistRepository.getArtistReleases(id, page, perPage, "year", "desc")
    }
}