package com.example.ponchomovies.presentation.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ponchomovies.domain.models.CastModel
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.usecase.GetCastUseCase
import com.example.ponchomovies.domain.usecase.GetMovieUseCase
import com.example.ponchomovies.framework.network.Failure
import com.example.ponchomovies.framework.state.ScreenState
import com.example.ponchomovies.utils.PonchoMoviesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase?,
    private val getCastUseCase: GetCastUseCase?
) : ViewModel() {

    private val _status = MutableLiveData<ScreenState>()
    val status: LiveData<ScreenState> get() = _status

    private val _movies = MutableLiveData<List<MovieResponse>>(emptyList())
    val movie: LiveData<List<MovieResponse>> get() = _movies

    private val _cast = MutableLiveData<List<CastModel>>()
    val cast: LiveData<List<CastModel>> = _cast

    fun getMovies(keyMovies: String?) {
        if (movie.value?.isEmpty() == true){
            _status.value = ScreenState.Loading
            keyMovies?.let { key ->
                viewModelScope.launch {
                    movieUseCase?.let {
                        it(key) { moviesResponse ->
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
                }
            } ?: handleGetMoviesError(Failure.ExceptionUnknown)
        }
    }

    fun getCast(movieId:String){
        viewModelScope.launch {
            getCastUseCase?.let {
                it(movieId = movieId){
                    Log.d("Cast response ", it.toString())
                    _cast.value = it?.cast
                }
            }
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