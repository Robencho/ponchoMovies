package com.example.ponchomovies.framework.state

import com.example.ponchomovies.domain.models.MovieItemEntity

sealed class ScreenState() {
    object Loading : ScreenState()
    class Success(val movies: List<MovieItemEntity> = emptyList()) : ScreenState()
    class Error(val message: String = "") : ScreenState()
    object Empty : ScreenState()
}
