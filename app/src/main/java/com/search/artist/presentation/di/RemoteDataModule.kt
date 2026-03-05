package com.search.artist.presentation.di

import com.search.artist.data.api.ArtistAPIService
import com.search.artist.data.repository.dataSource.ArtistRemoteDataSource
import com.search.artist.data.repository.dataSourceImpl.ArtistRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(
        artistAPIService: ArtistAPIService
    ): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(artistAPIService)
    }
}