package com.example.ponchomovies.data.remote

import com.example.ponchomovies.domain.models.CastResponseEntity
import com.example.ponchomovies.domain.models.PonchoMoviesEntity
import com.example.ponchomovies.framework.state.ScreenState
import kotlinx.coroutines.flow.Flow

interface IPonchoMoviesRemoteDataSource {
    suspend fun getCast(movieId: Int, castResponseEntity: (CastResponseEntity?) -> Unit)
}