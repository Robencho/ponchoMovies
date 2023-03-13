package com.example.ponchomovies.presentation.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.usecase.GetMovieUseCase
import com.example.ponchomovies.framework.network.Failure
import com.example.ponchomovies.framework.state.ScreenState
import com.example.ponchomovies.utils.PonchoMoviesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _status = MutableLiveData<ScreenState>()
    val status: LiveData<ScreenState> get() = _status

    private val _movies = MutableLiveData<List<MovieResponse>>(emptyList())
    val movie: LiveData<List<MovieResponse>> get() = _movies

    fun getMovies(keyMovies: String?) {
        if (movie.value?.isEmpty() == true){
            _status.value = ScreenState.Loading
            keyMovies?.let { key ->
                viewModelScope.launch {
                    movieUseCase(key) { moviesResponse ->
                        when (moviesResponse) {
                            is ScreenState.Success -> {
                                if (moviesResponse.movies.isNotEmpty())
                                    handleGetMoviesSuccess(moviesResponse.movies)
                            }
                            is ScreenState.Error -> {
                                handleGetMoviesError(Failure.ExceptionUnknown)
                            }
                            else -> handleGetMoviesError(Failure.ExceptionUnknown)
                        }
                    }
                }
            } ?: handleGetMoviesError(Failure.ExceptionUnknown)
        }
    }

    private fun handleGetMoviesSuccess(movies: List<MovieResponse>?) {
        _movies.value = movies
        _status.value = ScreenState.Success()
    }

    private fun handleGetMoviesError(exception: Exception) {
        _status.value = ScreenState.Error(exception.message ?: "paila parce!!!")
    }
}