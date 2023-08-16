package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.CastResponse
import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.utils.PonchoMoviesConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PonchoMoviesApi {
    @GET(PonchoMoviesConstants.EP_MOVIE_POPULAR)
    suspend fun getPonchoPopularMovies(@Query("api_key") userKey: String): MoviesResponse

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCasting(@Path("movie_id") movieId: Int, @Query("api_key") userKey: String): CastResponse
}