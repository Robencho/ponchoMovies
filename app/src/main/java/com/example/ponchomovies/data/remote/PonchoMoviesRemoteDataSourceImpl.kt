package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.models.toCastResponseEntity
import com.example.ponchomovies.utils.PonchoMoviesConstants.Companion.KEY_MOVIE
import javax.inject.Inject

class PonchoMoviesRemoteDataSourceImpl @Inject constructor(
    private val ponchoMoviesApi: PonchoMoviesApi
) {
    suspend fun getMoviesCoroutines(): List<MovieResponse> {
        return ponchoMoviesApi.getPonchoPopularMovies(KEY_MOVIE).results
    }
    suspend fun getCast(
        movieId: Int,
        castResponseEntity: (CastResponseEntity?) -> Unit
    ) {
        val response = ponchoMoviesApi.getCasting(movieId = movieId, userKey = KEY_MOVIE)
        castResponseEntity(
            response.toCastResponseEntity()
        )
    }
}