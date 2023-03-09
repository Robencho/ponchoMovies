package com.example.ponchomovies.data
import com.example.ponchomovies.framework.state.ScreenState

interface IMoviesRepository {
    suspend fun getMovies(category:String?):ScreenState
}