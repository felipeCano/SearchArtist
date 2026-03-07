package com.search.artist.presentation.viewmodel

import com.search.artist.MainDispatcherRule
import com.search.artist.domain.usecase.GetArtistReleasesUseCase
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ArtistReleaseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetArtistReleasesUseCase>()
    private lateinit var viewModel: ArtistReleaseViewModel

    @Test
    fun `getAlbumsPagingFlow returns flow and data is not null`() = runTest {
        viewModel = ArtistReleaseViewModel(useCase)

        val flow = viewModel.getAlbumsPagingFlow(1)

        Assert.assertNotNull(flow)
    }
}