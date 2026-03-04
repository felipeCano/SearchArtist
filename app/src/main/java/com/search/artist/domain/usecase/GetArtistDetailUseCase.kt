package com.search.artist.domain.usecase

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository

class GetArtistDetailUseCase(private val artistRepository: ArtistRepository) {

    suspend fun execute(id: Int): Resource<ArtistDetailResponse> {
        return artistRepository.getArtistDetail(id)
    }
}