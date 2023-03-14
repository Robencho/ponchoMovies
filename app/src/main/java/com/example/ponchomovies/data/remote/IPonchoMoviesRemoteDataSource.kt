package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.domain.models.PonchoMoviesEntity

interface IPonchoMoviesRemoteDataSource {

    suspend fun getMoviesApi(
        category: String?,
        moviesResponse: (PonchoMoviesEntity?) -> Unit
    )

    suspend fun getCast(movieId: Int, castResponseEntity: (CastResponseEntity?) -> Unit)
}