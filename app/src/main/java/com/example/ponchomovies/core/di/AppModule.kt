package com.example.ponchomovies.core.di

import com.example.ponchomovies.data.datasource.remote.MovieRemoteDataSource
import com.example.ponchomovies.core.network.PonchoMoviesApi
import com.example.ponchomovies.data.datasource.remote.MovieRemoteDataSourceImpl
import com.example.ponchomovies.data.repository.MoviesRepositoryImpl
import com.example.ponchomovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: PonchoMoviesApi
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MoviesRepositoryImpl(movieRemoteDataSource)
    }
}