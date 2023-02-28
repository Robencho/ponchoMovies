package com.example.ponchomovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.ponchomovies.navigation.MoviesNavigationHost
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PonchoMoviesTheme {
                val navController = rememberNavController()
                MoviesNavigationHost(navController = navController, moviesViewModel)
            }
        }
    }
}