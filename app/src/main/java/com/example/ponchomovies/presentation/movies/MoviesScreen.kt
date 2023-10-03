package com.example.ponchomovies.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ponchomovies.R.string
import com.example.ponchomovies.domain.model.Movie
import com.example.ponchomovies.presentation.common.ToolbarScreen
import com.example.ponchomovies.presentation.common.menu.BottomNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val moviePagingItems: LazyPagingItems<Movie> =
        moviesViewModel.uiState.collectAsLazyPagingItems()

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
                items(moviePagingItems.itemCount) { index ->
                    MoviesItemScreen(
                        moviesEntity = moviePagingItems[index]!!,
                        navController = navController
                    )
                }

                moviePagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {

                        }

                        loadState.refresh is LoadState.Error -> {

                        }

                        loadState.append is LoadState.Loading -> {

                        }

                        loadState.append is LoadState.Error -> {

                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
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

/*
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
}*/
