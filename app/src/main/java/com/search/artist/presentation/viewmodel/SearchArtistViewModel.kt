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

/**
 * ViewModel that manages the artist search screen state.
 * Handles query input, search initiation, and exposes a paginated flow of results.
 */
@HiltViewModel
class SearchArtistViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearchStarted = MutableStateFlow(false)
    val isSearchStarted = _isSearchStarted.asStateFlow()

    private val _confirmedQuery = MutableStateFlow("")

    val artistPagingFlow = _confirmedQuery
        .filter { it.isNotEmpty() }
        .flatMapLatest { query ->
            searchArtistUseCase.executePaging(query)
        }
        .cachedIn(viewModelScope)

    /**
     * Triggers the search process using the current query value.
     * Updates the search start state to true.
     */
    fun performSearch() {
        if (_searchQuery.value.isNotBlank()) {
            _isSearchStarted.value = true
            _confirmedQuery.value = _searchQuery.value
        }
    }

    /**
     * Updates the query input state.
     * Resets search status if the query is blank.
     * @param newQuery The updated search string.
     */
    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
        if (newQuery.isBlank()) {
            _isSearchStarted.value = false
        }
    }
}