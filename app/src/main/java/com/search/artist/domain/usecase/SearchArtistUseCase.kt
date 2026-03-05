package com.search.artist.domain.usecase

import androidx.paging.PagingData
import com.search.artist.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import com.search.artist.data.model.searchArtist.Result

class SearchArtistUseCase(private val artistRepository: ArtistRepository) {

    fun executePaging(query: String): Flow<PagingData<Result>> {
        return artistRepository.getArtistPagingFlow(query)
    }
}