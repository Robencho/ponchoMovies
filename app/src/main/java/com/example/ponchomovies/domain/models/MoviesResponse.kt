package com.example.ponchomovies.domain.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MoviesResponse.toMoviesResponseEntity() = PonchoMoviesEntity(
    page,
    results,
    totalPages,
    totalResults
)

