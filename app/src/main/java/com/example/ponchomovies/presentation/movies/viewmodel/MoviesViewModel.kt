package com.example.ponchomovies.presentation.movies.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ponchomovies.data.models.CastItemDto
import com.example.ponchomovies.domain.usecase.GetCastUseCase
import com.example.ponchomovies.domain.usecase.GetMovieUseCase
import com.example.ponchomovies.framework.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase?,
    private val getCastUseCase: GetCastUseCase?
) : ViewModel() {

    var isDarkThemeEnabled = mutableStateOf(false)
        private set

    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState

    private val _cast = MutableLiveData<List<CastItemDto>>()
    val cast: LiveData<List<CastItemDto>> = _cast

    fun getMovies(keyMovies: String?) {
        _uiState.value = ScreenState.Loading
        keyMovies?.let { key ->
            viewModelScope.launch {
                try {
                    movieUseCase?.let {
                        it(key) { moviesResponse ->
                            _uiState.value = moviesResponse
                        }
                    }
                } catch (exception: Exception) {
                    _uiState.value = ScreenState.Error("Ups algo falló!!!")
                }
            }
        }
    }

    fun getCast(movieId: String) {
        viewModelScope.launch {
            getCastUseCase?.let {
                it(movieId = movieId) {
                    Log.d("Cast response ", it.toString())
                    _cast.value = it?.cast
                }
            }
        }
    }

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }
}