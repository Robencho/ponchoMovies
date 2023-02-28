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
        MoviesNavigation(route = "movieDetail?title={title}&description={description}&imageUrl={imageUrl}&posterImage={posterImage}&releaseDate={releaseDate}") {
        fun createRoute(
            title: String,
            description: String,
            imageUrl: String,
            posterImage: String,
            releaseDate: String
        ): String {
            return "movieDetail?title=$title&description=$description&imageUrl=$imageUrl&posterImage=$posterImage&releaseDate=$releaseDate"
        }
    }
}

@Composable
fun MoviesNavigationHost(navController: NavHostController, viewModel: MoviesViewModel) {
    val ctx = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = MoviesNavigation.MoviesHome.route
    ) {
        composable(MoviesNavigation.MoviesHome.route) {
            PonchoMoviesTheme(useDarkTheme = false) {
                HomeScreen(navController)
            }
        }
        composable(MoviesNavigation.MoviesList.route) {
            PonchoMoviesTheme(useDarkTheme = false) {
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
            val description = navBackStackEntry.arguments?.getString("description")
            val imageUrl = navBackStackEntry.arguments?.getString("imageUrl")
            val posterImage = navBackStackEntry.arguments?.getString("posterImage")
            val releaseDate = navBackStackEntry.arguments?.getString("releaseDate")
            PonchoMoviesTheme(useDarkTheme = false) {
                MoviesDetailScreen(
                    navController = navController,
                    title = title,
                    description = description,
                    imageUrl = imageUrl,
                    posterImage = posterImage,
                    releaseDate = releaseDate
                )
            }
        }
    }
}