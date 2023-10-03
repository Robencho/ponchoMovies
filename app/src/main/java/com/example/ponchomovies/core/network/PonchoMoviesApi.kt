package com.example.ponchomovies.core.network

import com.example.ponchomovies.core.generic.dto.CastDto
import com.example.ponchomovies.core.generic.dto.ResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.CastItemDto
import com.example.ponchomovies.data.models.remote.dto.response.CastResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.MovieResponseDto
import com.example.ponchomovies.utils.PonchoMoviesConstants.Companion.EP_MOVIE_POPULAR
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.Flow

interface PonchoMoviesApi {
    @GET(EP_MOVIE_POPULAR)
    suspend fun getPonchoPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber:Int,
        @Query("language") language: String = "es-ES"
    ): ResponseDto<List<MovieResponseDto>>

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCasting(
        @Path("movie_id") movieId: Int,
        @Query("api_key") userKey: String,
        @Query("language") language:String = "es-ES"
    ): CastResponseDto
}