package com.example.ponchomovies.data

import com.example.ponchomovies.data.remote.PonchoMoviesRemoteDataSourceImpl
import com.example.ponchomovies.framework.state.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSourceImpl: PonchoMoviesRemoteDataSourceImpl
) : IMoviesRepository {
    override suspend fun getMovies(category: String?): ScreenState {
        return withContext(Dispatchers.IO) {
            try {
               val response =  moviesRemoteDataSourceImpl.getMoviesApi(category)
                ScreenState.Success(response.results)
            }catch (e:Exception){
                ScreenState.Error()
            }
        }
    }
}