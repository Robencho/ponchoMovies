package com.example.ponchomovies.data.models

import com.example.ponchomovies.domain.common.DTO
import com.example.ponchomovies.domain.common.Entity
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.models.PonchoMoviesEntity

data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieResponse>,
    val totalPages: Int,
    val totalResults: Int
): DTO() {

    companion object {
        val mapToDomain : (DTO) -> Entity = {movies->
            movies as MoviesResponseDto
            PonchoMoviesEntity(
                page = movies.page,
                results = movies.results,
                totalPages = movies.totalPages,
                totalResults = movies.totalResults
            )

        }
    }

    fun MoviesResponseDto.mapToDomainRubio(): PonchoMoviesEntity{
        return this.map(MoviesResponseDto.mapToDomain, this) as PonchoMoviesEntity? ?: PonchoMoviesEntity()
    }

}