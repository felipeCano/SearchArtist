package com.search.artist.presentation.viewmodel

import com.search.artist.MainDispatcherRule
import com.search.artist.domain.usecase.SearchArtistUseCase
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchArtistViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<SearchArtistUseCase>()
    private lateinit var viewModel: SearchArtistViewModel

    @Before
    fun setup() {
        viewModel = SearchArtistViewModel(useCase)
    }

    @Test
    fun `when query is changed, searchQuery state updates`() {
        viewModel.onQueryChanged("Daft Punk")
        Assert.assertEquals("Daft Punk", viewModel.searchQuery.value)
    }

    @Test
    fun `when performSearch is called with query, isSearchStarted becomes true`() {
        viewModel.onQueryChanged("Daft Punk")
        viewModel.performSearch()

        Assert.assertTrue(viewModel.isSearchStarted.value)
    }
}