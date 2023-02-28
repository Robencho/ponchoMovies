package com.example.ponchomovies.domain.models

import com.example.ponchomovies.domain.common.Entity


data class PonchoMoviesEntity(
    val page: Int = 0,
    val results: List<MovieResponse> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
): Entity()
