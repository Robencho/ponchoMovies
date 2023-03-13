package com.example.ponchomovies.data

import com.example.ponchomovies.data.remote.PonchoMoviesRemoteDataSourceImpl
import com.example.ponchomovies.framework.state.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSourceImpl: PonchoMoviesRemoteDataSourceImpl
) : IMoviesRepository {
    override suspend fun getMovies(category: String?, response: (ScreenState) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                moviesRemoteDataSourceImpl.getMoviesApi(category) { moviesResponse ->
                    moviesResponse?.results?.let { ScreenState.Success(it) }?.let {
                        response(
                            it
                        )
                    }
                }
            } catch (e: Exception) {
                ScreenState.Error()
            }
        }
    }
}