package com.search.artist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.search.artist.domain.usecase.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchArtistViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _confirmedQuery = MutableStateFlow("")

    val artistPagingFlow = _confirmedQuery
        .filter { it.isNotEmpty() }
        .flatMapLatest { query ->
            searchArtistUseCase.executePaging(query)
        }
        .cachedIn(viewModelScope)

    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun performSearch() {
        if (_searchQuery.value.trim().length >= 3) {
            _confirmedQuery.value = _searchQuery.value
        }
    }
}