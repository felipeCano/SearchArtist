package com.search.artist.presentation.di

import com.search.artist.data.repository.ArtistRepositoryImpl
import com.search.artist.data.repository.dataSource.ArtistRemoteDataSource
import com.search.artist.domain.repository.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(artistRemoteDataSource)
    }
}