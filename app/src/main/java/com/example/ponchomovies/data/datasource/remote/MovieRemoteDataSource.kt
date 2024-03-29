package com.example.ponchomovies.data.datasource.remote

import com.example.ponchomovies.core.generic.dto.ResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.CastResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.MovieResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.VideosDto

interface MovieRemoteDataSource {
    suspend fun getMovies(
        apiKey: String,
        pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>

    suspend fun getCast(movieId: Int): CastResponseDto

    suspend fun getVideosByMovie(movieId: Int, apiKey: String, appendToResponse: String): VideosDto
}