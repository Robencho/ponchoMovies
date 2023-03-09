package com.example.ponchomovies.framework.state

import com.example.ponchomovies.domain.models.MovieResponse

sealed class ScreenState() {
    object Loading : ScreenState()
    class Success(val movies:List<MovieResponse> = emptyList()) : ScreenState()
    class Error(val message:String = "") : ScreenState()
    object Empty : ScreenState()
}
