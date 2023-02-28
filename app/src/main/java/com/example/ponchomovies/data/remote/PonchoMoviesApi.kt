package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.utils.PonchoMoviesConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface PonchoMoviesApi {
    @GET(PonchoMoviesConstants.EP_MOVIE_POPULAR)
    fun getPonchoPopularMovies(@Query("api_key")userKey:String): retrofit2.Call<MoviesResponse>
}