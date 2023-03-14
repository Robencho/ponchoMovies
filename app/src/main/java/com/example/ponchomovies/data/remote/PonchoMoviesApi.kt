package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.CastResponse
import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.utils.PonchoMoviesConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PonchoMoviesApi {
    @GET(PonchoMoviesConstants.EP_MOVIE_POPULAR)
    fun getPonchoPopularMovies(@Query("api_key")userKey:String): retrofit2.Call<MoviesResponse>

    @GET("3/movie/{movie_id}/credits")
    fun getCasting(@Path("movie_id")movieId:Int, @Query("api_key")userKey: String): retrofit2.Call<CastResponse>

    // Response{protocol=h2, code=401, message=, url=https://api.themoviedb.org/3/movie/?&movie_id=505642}

    // /xndWFsBlClOJFRdhSt4NBwiPq2o.jpg
}