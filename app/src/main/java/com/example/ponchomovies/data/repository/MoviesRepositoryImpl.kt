package com.example.ponchomovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ponchomovies.core.app.Constants
import com.example.ponchomovies.data.datasource.remote.MovieRemoteDataSource
import com.example.ponchomovies.data.models.remote.mapper.mapFromListModel
import com.example.ponchomovies.data.repository.paging.MoviePagingSource
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.domain.model.Movie
import com.example.ponchomovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
):MovieRepository {
    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource)
            }
        ).flow
    }

    override suspend fun getCast(movieId:Int): List<Cast> {
        return remoteDataSource.getCast(movieId).cast.mapFromListModel()
    }
}