package com.example.ponchomovies.domain.usecase

import androidx.paging.PagingData
import com.example.ponchomovies.core.generic.useCase.BaseUseCase
import com.example.ponchomovies.domain.model.Movie
import com.example.ponchomovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Unit, Flow<PagingData<Movie>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}