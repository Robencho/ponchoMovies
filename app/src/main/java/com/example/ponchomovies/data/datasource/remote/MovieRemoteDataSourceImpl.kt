package com.example.ponchomovies.data.datasource.remote

import com.example.ponchomovies.core.app.Constants
import com.example.ponchomovies.core.generic.dto.ResponseDto
import com.example.ponchomovies.core.network.PonchoMoviesApi
import com.example.ponchomovies.data.models.remote.dto.response.CastResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.MovieResponseDto
import com.example.ponchomovies.data.models.remote.dto.response.VideosDto
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: PonchoMoviesApi
) : MovieRemoteDataSource {
    override suspend fun getMovies(
        apiKey: String,
        pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>> {
        return api.getPonchoPopularMovies(apiKey = apiKey, pageNumber = pageNumber)
    }

    override suspend fun getCast(movieId: Int): CastResponseDto {
        return api.getCasting(movieId, Constants.MOVIE_API_KEY)
    }

    override suspend fun getVideosByMovie(
        movieId: Int,
        apiKey: String,
        appendToResponse: String
    ): VideosDto {
        return api.getVideos(
            movieId = movieId,
            apiKey = apiKey,
            appendToResponse = appendToResponse
        )
    }
}