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
        response: (data: PonchoMoviesEntity?) -> Unit
    ) {
        when (category) {
            PonchoMoviesConstants.EP_MOVIE_POPULAR -> {
                ponchoMoviesApi.getPonchoPopularMovies(KEY_MOVIE)
                    .enqueue(object : Callback<MoviesResponse> {
                        override fun onResponse(
                            call: Call<MoviesResponse>,
                            response: Response<MoviesResponse>
                        ) {
                            response(response.body()?.toMoviesResponseEntity())
                        }

                        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                        }
                    })
            }
        }
    }

}