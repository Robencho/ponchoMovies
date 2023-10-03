package com.example.ponchomovies.presentation.movies.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.domain.model.Movie
import com.example.ponchomovies.domain.usecase.GetCastUseCase
import com.example.ponchomovies.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase?,
    private val getCastUseCase: GetCastUseCase?
) : ViewModel() {

    var isDarkThemeEnabled = mutableStateOf(false)
        private set

    private val _uiState = MutableStateFlow<PagingData<Movie>>(value = PagingData.empty())
    val uiState: MutableStateFlow<PagingData<Movie>> = _uiState

    private val _cast = MutableLiveData<List<Cast>>()
    val cast: LiveData<List<Cast>> = _cast

    init {
        viewModelScope.launch {
            onEvent(MoviesEvent.GetMoviesEvent)
        }
    }

    private fun onEvent(event: MoviesEvent) {
        viewModelScope.launch {
            when (event) {
                is MoviesEvent.GetMoviesEvent -> {
                    getMovies2()
                }
            }
        }
    }

    private suspend fun getMovies2() {
        getMovieUseCase?.execute(Unit)
            ?.distinctUntilChanged()
            ?.cachedIn(viewModelScope)
            ?.collect {
                _uiState.value = it
            }
    }

    fun getCast(movieId: Int) {
        viewModelScope.launch {
            val response = getCastUseCase?.execute(movieId = movieId)?.filter { it.profile_path.isNotEmpty() }
            _cast.value = response.orEmpty()
        }
    }

    sealed class MoviesEvent() {
        object GetMoviesEvent : MoviesEvent()
    }

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }
}