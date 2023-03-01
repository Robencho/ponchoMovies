package com.example.ponchomovies.data

import com.example.ponchomovies.data.remote.PonchoMoviesRemoteDataSourceImpl
import com.example.ponchomovies.domain.models.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSourceImpl: PonchoMoviesRemoteDataSourceImpl
) : IMoviesRepository {
    override suspend fun getMovies(
        category: String?,
        response: (data: List<MovieResponse>) -> Unit
    ) {
        withContext(Dispatchers.IO){
            moviesRemoteDataSourceImpl.getMoviesApi(category, response = {
                response(it?.results?: emptyList())
            })
        }
    }
}