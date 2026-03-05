package com.search.artist.presentation.di

import com.search.artist.BuildConfig
import com.search.artist.data.api.ArtistAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    private val BASE_URL = "https://api.discogs.com/"
    private val API_KEY = "RzcAeyPSRsNkbazbqcRKhkCVIMDcPMRZXmbwNDgm"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithApiKey = originalRequest.newBuilder()
                .header("User-Agent", "SearchArtistApp/1.0 (${BuildConfig.APPLICATION_ID})")
                .header("Authorization", "Discogs token=$API_KEY")
                .build()
            chain.proceed(requestWithApiKey)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideArtistAPIService(retrofit: Retrofit): ArtistAPIService {
        return retrofit.create(ArtistAPIService::class.java)
    }
}