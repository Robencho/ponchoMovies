package com.example.ponchomovies.domain.usecase

import com.example.ponchomovies.core.generic.useCase.BaseUseCase
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetCastUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Int, List<Cast>> {
    override suspend fun execute(movieId: Int): List<Cast> =
        repository.getCast(movieId = movieId)
}