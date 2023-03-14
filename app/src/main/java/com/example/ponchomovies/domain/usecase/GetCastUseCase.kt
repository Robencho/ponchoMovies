package com.example.ponchomovies.domain.usecase

import com.example.ponchomovies.data.MoviesRepositoryImpl
import com.example.ponchomovies.domain.models.CastResponseEntity
import javax.inject.Inject

class GetCastUseCase @Inject constructor(
    private val moviesRepositoryImpl: MoviesRepositoryImpl
) {
    suspend operator fun invoke(movieId: String, responseEntity: (CastResponseEntity?) -> Unit) {
        moviesRepositoryImpl.getCast(movieId) {
            responseEntity(it)
        }
    }
}