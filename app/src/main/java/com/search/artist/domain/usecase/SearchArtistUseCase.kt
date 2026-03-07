package com.search.artist.domain.usecase

import androidx.paging.PagingData
import com.search.artist.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import com.search.artist.data.model.searchArtist.Result

/**
 * Use case responsible for searching artists by a query string.
 * Provides paginated results to ensure efficient data loading.
 */
class SearchArtistUseCase(private val artistRepository: ArtistRepository) {
    /**
     * Executes a paginated search for artists matching the given query.
     * @param query The search term entered by the user.
     * @return A [Flow] of [PagingData] containing the artist results.
     */
    fun executePaging(query: String): Flow<PagingData<Result>> {
        return artistRepository.getArtistPagingFlow(query)
    }
}