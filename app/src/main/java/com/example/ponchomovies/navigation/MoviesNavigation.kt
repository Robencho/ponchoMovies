package com.example.ponchomovies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ponchomovies.presentation.home.HomeScreen
import com.example.ponchomovies.presentation.movies.MoviesDetailScreen
import com.example.ponchomovies.presentation.movies.MoviesScreen
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme

sealed class MoviesNavigation(val route: String) {
    object MoviesHome : MoviesNavigation(route = "moviesHome")
    object MoviesList : MoviesNavigation(route = "moviesList")
    object MovieDetail :
        MoviesNavigation(route = "movieDetail?title={title}&description={description}&imageUrl={imageUrl}&posterImage={posterImage}&releaseDate={releaseDate}&id={id}") {
        fun createRoute(
            title: String,
            description: String,
            backGroundPath: String,
            posterImage: String,
            releaseDate: String,
            movieId: String
        ): String {
            return "movieDetail?title=$title&description=$description&imageUrl=$backGroundPath&posterImage=$posterImage&releaseDate=$releaseDate&id=$movieId"
        }
    }
}

@Composable
fun MoviesNavigationHost(
    navController: NavHostController,
    viewModel: MoviesViewModel,
    isDarkTheme: Boolean
) {
    val ctx = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = MoviesNavigation.MoviesHome.route
    ) {
        composable(MoviesNavigation.MoviesHome.route) {
            PonchoMoviesTheme(useDarkTheme = isDarkTheme) {
                HomeScreen(navController, moviesViewModel = viewModel)
            }
        }
        composable(MoviesNavigation.MoviesList.route) {
            PonchoMoviesTheme(useDarkTheme = isDarkTheme) {
                MoviesScreen(navController = navController, moviesViewModel = viewModel)
            }
        }
        composable(route = MoviesNavigation.MovieDetail.route, arguments = listOf(
            navArgument("title") {
                defaultValue = "title default"
            },
            navArgument("description") {
                defaultValue = "description default"
            }
        )) { navBackStackEntry ->
            val title = navBackStackEntry.arguments?.getString("title")
            val movieId = navBackStackEntry.arguments?.getString("id")
            val description = navBackStackEntry.arguments?.getString("description")
            val imageUrl = navBackStackEntry.arguments?.getString("imageUrl")
            val posterImage = navBackStackEntry.arguments?.getString("posterImage")
            val releaseDate = navBackStackEntry.arguments?.getString("releaseDate")
            PonchoMoviesTheme(useDarkTheme = isDarkTheme) {
                MoviesDetailScreen(
                    viewModel = viewModel,
                    navController = navController,
                    movieId = movieId ?: "",
                    title = title,
                    description = description,
                    backgroundPath = imageUrl,
                    posterImage = posterImage,
                    releaseDate = releaseDate
                )
            }
        }
    }
}