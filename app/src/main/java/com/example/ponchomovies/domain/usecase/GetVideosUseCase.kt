package com.example.ponchomovies.domain.usecase

import com.example.ponchomovies.core.generic.useCase.BaseUseCase
import com.example.ponchomovies.domain.model.Video
import com.example.ponchomovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: MovieRepository
):BaseUseCase<Int, List<Video>> {
    override suspend fun execute(movieId: Int): List<Video> {
       return repository.getVideosByMovieId(movieId = movieId)
    }
}