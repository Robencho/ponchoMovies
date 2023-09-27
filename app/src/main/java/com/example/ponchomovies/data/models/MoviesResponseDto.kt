package com.example.ponchomovies.data.models

data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieItemDto>,
    val totalPages: Int,
    val totalResults: Int
)