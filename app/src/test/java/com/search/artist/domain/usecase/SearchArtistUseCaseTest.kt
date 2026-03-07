package com.search.artist.domain.usecase

import androidx.paging.PagingData
import com.search.artist.domain.repository.ArtistRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import com.search.artist.data.model.searchArtist.Result
import org.junit.Test

class SearchArtistUseCaseTest {
    private val repository = mockk<ArtistRepository>()
    private val useCase = SearchArtistUseCase(repository)

    @Test
    fun `executePaging returns flow from repository`() = runTest {
        val mockFlow = flowOf(PagingData.empty<Result>())
        every { repository.getArtistPagingFlow("query") } returns mockFlow

        val result = useCase.executePaging("query")

        assert(result == mockFlow)
        verify { repository.getArtistPagingFlow("query") }
    }
}