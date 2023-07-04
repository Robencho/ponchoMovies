package com.example.ponchomovies.data.remote

import android.util.Log
import com.example.ponchomovies.domain.models.CastResponse
import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.domain.models.toCastResponseEntity
import com.example.ponchomovies.framework.state.ScreenState
import com.example.ponchomovies.utils.PonchoMoviesConstants.Companion.KEY_MOVIE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PonchoMoviesRemoteDataSourceImpl @Inject constructor(
    private val ponchoMoviesApi: PonchoMoviesApi
) : IPonchoMoviesRemoteDataSource {
    suspend fun getMoviesWitFlow(category: String): Flow<ScreenState> = flow {
        val moviesResponse = processMoviesData()
        emit(
            ScreenState.Success(
                moviesResponse
            )
        )
    }

    private suspend fun processMoviesData(): List<MovieResponse> =
        suspendCoroutine { continuation ->
            ponchoMoviesApi.getPonchoPopularMovies(userKey = KEY_MOVIE)
                .enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        continuation.resume(response.body()?.results ?: emptyList())
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.d("Error", "Paila parce!!!")
                        continuation.resume(emptyList())
                    }
                }
                )
        }

    override suspend fun getCast(
        movieId: Int,
        castResponseEntity: (CastResponseEntity?) -> Unit
    ) {
        ponchoMoviesApi.getCasting(movieId = movieId, userKey = KEY_MOVIE)
            .enqueue(object : Callback<CastResponse> {
                override fun onResponse(
                    call: Call<CastResponse>,
                    response: Response<CastResponse>
                ) {
                    castResponseEntity(response.body()?.toCastResponseEntity())
                }

                override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}