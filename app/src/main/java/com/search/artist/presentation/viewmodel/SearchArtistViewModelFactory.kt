package com.search.artist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.search.artist.domain.usecase.SearchArtistUseCase

class SearchArtistViewModelFactory(
    private val searchArtistUseCase: SearchArtistUseCase,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchArtistViewModel(searchArtistUseCase, app) as T

    }
}