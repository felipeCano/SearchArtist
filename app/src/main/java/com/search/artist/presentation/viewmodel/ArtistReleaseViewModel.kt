package com.search.artist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.search.artist.data.model.artistiReleases.Release
import com.search.artist.data.paging.ArtistReleasePagingSource
import com.search.artist.domain.usecase.GetArtistReleasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArtistReleaseViewModel @Inject constructor(
    private val getArtistreleasesUseCase: GetArtistReleasesUseCase
) : ViewModel() {

    fun getAlbumsPagingFlow(artistId: Int): Flow<PagingData<Release>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = {
                ArtistReleasePagingSource(getArtistreleasesUseCase, artistId)
            }
        ).flow.cachedIn(viewModelScope)
    }
}