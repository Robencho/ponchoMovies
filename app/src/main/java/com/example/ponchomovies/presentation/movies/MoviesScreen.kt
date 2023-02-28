package com.example.ponchomovies.presentation.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.utils.PonchoMoviesConstants

@Composable
fun MoviesScreen(navController: NavController, moviesViewModel: MoviesViewModel) {

    /** ojo guiarme en este proyecto  https://github.com/YassinAJDI/PopularMovies*/
    /** Movies theme compose: https://material.io/blog/material-theme-builder*/

    val movies: List<MovieResponse> by moviesViewModel.movie.observeAsState(initial = emptyList())
    val isLoadingMovies: Boolean by moviesViewModel.isLoading.observeAsState(initial = true)
    moviesViewModel.getMovies()
    PonchoMoviesTheme() {
        if (isLoadingMovies) {
            LoadingScreen()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onSecondaryContainer)
            ) {
                items(movies) { movieItem ->
                    MoviesItemScreen(moviesEntity = movieItem, navController = navController)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MoviesItemScreen(moviesEntity: MovieResponse, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier =
            Modifier
                .clickable {
                    navController.navigate(
                        MoviesNavigation.MovieDetail.createRoute(
                            moviesEntity.originalTitle,
                            moviesEntity.overview,
                            moviesEntity.backdropPath,
                            moviesEntity.posterPath,
                            moviesEntity.releaseDate
                        )
                    )
                }
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${PonchoMoviesConstants.URL_IMAGES}/${moviesEntity.posterPath}")
                        .crossfade(3000)
                        .build(),
                    contentDescription = "*",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )
                ProgressComponent(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    moviesEntity.voteAverage.toString()
                )
            }
            Text(
                text = moviesEntity.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = moviesEntity.releaseDate,
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun ProgressComponent(modifier: Modifier, percent: String) {
    val percentOk = percent.toFloat()
    val new = percentOk / 10
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(50.dp)
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = new,
            color = if (new < 0.7f) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceTint
        )
        Text(
            text = "$percent",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesItemPreview() {
    val ctx = LocalContext.current
    PonchoMoviesTheme() {
        MoviesItemScreen(
            moviesEntity = MovieResponse(),
            navController = NavController(ctx)
        )
    }
    //ProgressComponent()
}


// todo move this function to view model