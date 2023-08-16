package com.example.ponchomovies.data

import com.example.ponchomovies.data.remote.PonchoMoviesRemoteDataSourceImpl
import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.framework.state.ScreenState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSourceImpl: PonchoMoviesRemoteDataSourceImpl
) {
    suspend fun getMoviesWithCoroutines(response: (ScreenState) -> Unit) {
        val responseCallToApi = moviesRemoteDataSourceImpl.getMoviesCoroutines()
        response(
            if (responseCallToApi.isEmpty())
                ScreenState.Error("Ups!!! la lista está vacía")
            else ScreenState.Success(
                responseCallToApi
            )
        )
    }

    suspend fun getCast(movieId: String, castResponse: (CastResponseEntity?) -> Unit) {
        moviesRemoteDataSourceImpl.getCast(movieId.toInt()) {
            castResponse(it)
        }
    }
}