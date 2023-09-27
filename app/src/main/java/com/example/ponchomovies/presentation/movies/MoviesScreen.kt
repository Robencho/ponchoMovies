@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ponchomovies.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ponchomovies.R.string
import com.example.ponchomovies.framework.state.ScreenState
import com.example.ponchomovies.presentation.common.ToolbarScreen
import com.example.ponchomovies.presentation.common.menu.BottomNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.utils.PonchoMoviesConstants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val uiState by moviesViewModel.uiState.collectAsState()
    when (uiState) {
        is ScreenState.Loading -> {
            LoadingScreen()
            moviesViewModel.getMovies(PonchoMoviesConstants.EP_MOVIE_POPULAR)
        }

        is ScreenState.Success -> {
            val moviesResponse = (uiState as ScreenState.Success).movies
            Scaffold(
                modifier = Modifier,
                topBar = {
                    ToolbarScreen(onIconPressed = {
                        navController.popBackStack()
                    }, title = stringResource(id = string.top_bar_title_home))
                },
                content = {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        items(items = moviesResponse) { movieItem ->
                            MoviesItemScreen(
                                moviesEntity = movieItem,
                                navController = navController
                            )
                        }
                    }
                },
                bottomBar = {
                    BottomNavigation(navController = navController)
                }
            )
        }

        is ScreenState.Error -> {
            ShowError()
        }

        else -> Unit
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ocurrió un error inesperado.")
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesItemPreview() {
    val ctx = LocalContext.current
    PonchoMoviesTheme() {
        MoviesScreen(
            navController = NavController(ctx),
            moviesViewModel = MoviesViewModel(null, null)
        )
    }
}