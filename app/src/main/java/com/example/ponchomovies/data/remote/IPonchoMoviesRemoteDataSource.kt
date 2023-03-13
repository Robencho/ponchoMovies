package com.example.ponchomovies.data.remote
import com.example.ponchomovies.domain.models.PonchoMoviesEntity

interface IPonchoMoviesRemoteDataSource {

    suspend fun getMoviesApi(
        category:String?,
        moviesResponse: (PonchoMoviesEntity?) -> Unit
    )
}