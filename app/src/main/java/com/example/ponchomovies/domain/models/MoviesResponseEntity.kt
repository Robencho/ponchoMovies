package com.example.ponchomovies.domain.models

import com.example.ponchomovies.data.models.MovieItemDto
import com.example.ponchomovies.domain.common.Entity


data class MoviesResponseEntity(
    val page: Int = 0,
    val results: List<MovieItemDto> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
): Entity()
