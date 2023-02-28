package com.example.ponchomovies.data

import com.example.ponchomovies.domain.models.MovieResponse
interface IMoviesRepository {
    suspend fun getMovies(category:String?, response:(data: List<MovieResponse>)-> Unit)
}