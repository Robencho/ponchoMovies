package com.example.ponchomovies.domain.usecase

import com.example.ponchomovies.data.MoviesRepositoryImpl
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.utils.PonchoMoviesConstants
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val moviesRepositoryImpl: MoviesRepositoryImpl
) {
    suspend operator fun invoke(response:(data:List<MovieResponse>)-> Unit){
        moviesRepositoryImpl.getMovies(
            PonchoMoviesConstants.EP_MOVIE_POPULAR,
            response = {
              response(it)
            }
        )
    }
}