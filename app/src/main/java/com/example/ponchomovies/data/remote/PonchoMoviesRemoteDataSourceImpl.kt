package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.*
import com.example.ponchomovies.utils.PonchoMoviesConstants
import com.example.ponchomovies.utils.PonchoMoviesConstants.Companion.KEY_MOVIE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PonchoMoviesRemoteDataSourceImpl @Inject constructor(
    private val ponchoMoviesApi: PonchoMoviesApi
) : IPonchoMoviesRemoteDataSource {

    override suspend fun getMoviesApi(
        category: String?,
        moviesResponse: (PonchoMoviesEntity?) -> Unit
    ){
        when (category) {
            PonchoMoviesConstants.EP_MOVIE_POPULAR -> {
                ponchoMoviesApi.getPonchoPopularMovies(KEY_MOVIE)
                    .enqueue(object : Callback<MoviesResponse> {
                        override fun onResponse(
                            call: Call<MoviesResponse>,
                            response: Response<MoviesResponse>
                        ) {
                           moviesResponse(response.body()?.toMoviesResponseEntity())
                        }
                        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) = Unit
                    })
            }
        }
    }

    override suspend fun getCast(
        movieId: Int,
        castResponseEntity: (CastResponseEntity?) -> Unit
    ) {
        ponchoMoviesApi.getCasting(movieId = movieId, userKey = KEY_MOVIE).enqueue(object : Callback<CastResponse>{
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                castResponseEntity(response.body()?.toCastResponseEntity())
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}