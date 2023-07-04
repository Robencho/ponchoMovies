package com.example.ponchomovies.data

import com.example.ponchomovies.data.remote.PonchoMoviesRemoteDataSourceImpl
import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.framework.state.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSourceImpl: PonchoMoviesRemoteDataSourceImpl
) : IMoviesRepository {
    override suspend fun getMoviesWitFlow(category: String, response: (ScreenState) -> Unit) {
        withContext(Dispatchers.IO) {
            moviesRemoteDataSourceImpl.getMoviesWitFlow(category)
                .catch {
                    response(ScreenState.Error("Error call to service!!!"))
                }
                .collect {
                    response(it)
                }
        }
    }
    suspend fun getCast(movieId: String, castResponse: (CastResponseEntity?) -> Unit) {
        withContext(Dispatchers.IO) {
            moviesRemoteDataSourceImpl.getCast(movieId.toInt()) {
                castResponse(it)
            }
        }

    }
}