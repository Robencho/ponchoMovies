package com.example.ponchomovies.domain.usecase

import com.example.ponchomovies.data.MoviesRepositoryImpl
import com.example.ponchomovies.framework.state.ScreenState
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val moviesRepositoryImpl: MoviesRepositoryImpl
) {
    suspend operator fun invoke(parameters: String): ScreenState{
        return moviesRepositoryImpl.getMovies(parameters)
    }
}