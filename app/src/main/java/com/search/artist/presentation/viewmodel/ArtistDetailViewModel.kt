package com.search.artist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.usecase.GetArtistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<ArtistDetailResponse>>(Resource.Idle())
    val uiState: StateFlow<Resource<ArtistDetailResponse>> = _uiState

    fun loadArtistDetail(artistId: Int) {
        viewModelScope.launch {
            _uiState.value = Resource.Loading()
            try {
                val result = getArtistDetailUseCase.execute(artistId)
                _uiState.value = result
            } catch (e: Exception) {
                _uiState.value = Resource.Error(message = e.message ?: "An unexpected error occurred")
            }
        }
    }
}