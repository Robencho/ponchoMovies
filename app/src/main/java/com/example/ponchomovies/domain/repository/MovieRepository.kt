package com.example.ponchomovies.domain.repository

import androidx.paging.PagingData
import com.example.ponchomovies.core.generic.dto.CastDto
import com.example.ponchomovies.data.models.remote.dto.response.CastItemDto
import com.example.ponchomovies.data.models.remote.dto.response.CastResponseDto
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>

    suspend fun getCast(movieId:Int): List<Cast>
}