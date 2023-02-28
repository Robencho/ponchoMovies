package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.domain.models.PonchoMoviesEntity

interface IPonchoMoviesRemoteDataSource {

    suspend fun getMoviesApi(
        category:String?,
        response: (data: PonchoMoviesEntity?) -> Unit
    )
}