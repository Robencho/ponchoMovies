package com.example.ponchomovies.presentation.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase
): ViewModel() {

    private val _movies = MutableLiveData<List<MovieResponse>>()
    val movie : LiveData<List<MovieResponse>> get() = _movies

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovies(){
        viewModelScope.launch {
            movieUseCase {
                _movies.value = it
                _isLoading.value = false
            }
        }
    }

}