package com.example.ponchomovies.di

import com.example.ponchomovies.data.remote.PonchoMoviesApi
import com.example.ponchomovies.utils.PonchoMoviesConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PonchoMoviesConstants.API_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): PonchoMoviesApi =
        retrofit.create(PonchoMoviesApi::class.java)
}