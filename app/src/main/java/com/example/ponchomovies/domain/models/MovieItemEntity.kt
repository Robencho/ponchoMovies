package com.example.ponchomovies.domain.models

import com.example.ponchomovies.domain.common.Entity

data class MovieItemEntity(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0,
    val videos: List<MovieVideoEntity> = emptyList()
) : Entity()

data class MovieVideosEntity(
    val results: List<MovieVideoEntity> = emptyList()
): Entity()

data class MovieVideoEntity(
    val iso_639_1: String = "",
    val iso_3166_1: String = "",
    val name: String = "",
    val key: String = "",
    val site: String = "",
    val size: Int = 0,
    val type: String = "",
    val official: Boolean = false,
    val publishedAt: String = "",
    val id: String = ""
): Entity()
