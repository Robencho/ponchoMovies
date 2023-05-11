package com.example.ponchomovies.data
import com.example.ponchomovies.domain.models.MoviesResponse
import com.example.ponchomovies.framework.state.ScreenState
import kotlinx.coroutines.flow.Flow


interface IMoviesRepository {
    suspend fun getMoviesWitFlow(category:String, response: (ScreenState) -> Unit)
}