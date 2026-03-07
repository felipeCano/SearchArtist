package com.search.artist.domain.usecase

import com.search.artist.data.model.artistiReleases.ArtistReleasesResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetArtistReleasesUseCaseTest {
    private val repository = mockk<ArtistRepository>()
    private val useCase = GetArtistReleasesUseCase(repository)

    @Test
    fun `execute returns success with expected parameters`() = runTest {
        val mockResponse = mockk<ArtistReleasesResponse>()
        coEvery { repository.getArtistReleases(123, 1, 30, "year", "desc") } returns Resource.Success(mockResponse)

        val result = useCase.execute(123, 1, 30)

        assert(result is Resource.Success)
        coVerify { repository.getArtistReleases(123, 1, 30, "year", "desc") }
    }
}