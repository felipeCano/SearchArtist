package com.search.artist.presentation.di

import com.search.artist.domain.repository.ArtistRepository
import com.search.artist.domain.usecase.GetArtistDetailUseCase
import com.search.artist.domain.usecase.GetArtistReleasesUseCase
import com.search.artist.domain.usecase.SearchArtistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchArtistUseCase(artistRepository: ArtistRepository): SearchArtistUseCase {
        return SearchArtistUseCase(artistRepository)
    }

    @Provides
    @Singleton
    fun provideGetArtistReleasesUseCase(artistRepository: ArtistRepository): GetArtistReleasesUseCase {
        return GetArtistReleasesUseCase(artistRepository)
    }

    @Provides
    @Singleton
    fun provideGetArtistDetailUseCase(artistRepository: ArtistRepository): GetArtistDetailUseCase {
        return GetArtistDetailUseCase(artistRepository)
    }
}