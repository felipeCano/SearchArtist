package com.search.artist.domain.usecase

import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.repository.ArtistRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class GetArtistDetailUseCaseTest {
    private val repository = mockk<ArtistRepository>()
    private val useCase = GetArtistDetailUseCase(repository)

    @Test
    fun `execute returns success when repository succeeds`() = runTest {
        val mockResponse = mockk<ArtistDetailResponse>()
        coEvery { repository.getArtistDetail(123) } returns Resource.Success(mockResponse)

        val result = useCase.execute(123)

        assertTrue(result is Resource.Success && result.data == mockResponse)
        coVerify { repository.getArtistDetail(123) }
    }
}