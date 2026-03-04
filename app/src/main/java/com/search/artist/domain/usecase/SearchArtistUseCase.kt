package com.search.artist.domain.usecase

import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository

class SearchArtistUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute(
        query: String,
        page: Int,
        perPage: Int = 30
    ): Resource<SearchArtistResponse> {
        return artistRepository.searchArtist(query, page, perPage)
    }
}