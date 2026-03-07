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

/**
 * ViewModel responsible for managing the state and data flow for the artist releases screen.
 * It utilizes the Android Paging 3 library to provide a stream of paginated data.
 */
@HiltViewModel
class ArtistReleaseViewModel @Inject constructor(
    private val getArtistreleasesUseCase: GetArtistReleasesUseCase
) : ViewModel() {

    /**
     * Creates and returns a [Flow] of [PagingData] for the specified artist.
     * The [Pager] uses the [ArtistReleasePagingSource] to fetch releases lazily.
     * * @param artistId The unique ID of the artist whose releases are being fetched.
     * @return A [Flow] of [PagingData] containing [Release] objects, cached in the [viewModelScope].
     */
    fun getAlbumsPagingFlow(artistId: Int): Flow<PagingData<Release>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = {
                ArtistReleasePagingSource(getArtistreleasesUseCase, artistId)
            }
        ).flow.cachedIn(viewModelScope)
    }
}