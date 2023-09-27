package com.example.ponchomovies.data.remote

import com.example.ponchomovies.data.models.MoviesResponseDto
import com.example.ponchomovies.data.models.CastResponseDto
import com.example.ponchomovies.utils.PonchoMoviesConstants.Companion.EP_MOVIE_POPULAR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PonchoMoviesApi {
    @GET(EP_MOVIE_POPULAR)
    suspend fun getPonchoPopularMovies(@Query("api_key") userKey: String): MoviesResponseDto

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCasting(
        @Path("movie_id") movieId: Int,
        @Query("api_key") userKey: String
    ): CastResponseDto
}