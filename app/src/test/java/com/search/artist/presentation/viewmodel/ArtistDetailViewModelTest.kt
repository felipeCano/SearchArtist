package com.search.artist.presentation.viewmodel

import com.search.artist.MainDispatcherRule
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.usecase.GetArtistDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ArtistDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetArtistDetailUseCase>()
    private lateinit var viewModel: ArtistDetailViewModel

    @Test
    fun `loadArtistDetail updates uiState to success`() = runTest {
        val mockData = mockk<ArtistDetailResponse>()
        coEvery { useCase.execute(1) } returns Resource.Success(mockData)

        viewModel = ArtistDetailViewModel(useCase)

        viewModel.loadArtistDetail(1)

        Assert.assertTrue(viewModel.uiState.value is Resource.Success)
    }
}