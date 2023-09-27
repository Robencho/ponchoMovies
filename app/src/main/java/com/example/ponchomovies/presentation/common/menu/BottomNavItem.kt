package com.example.ponchomovies.presentation.common.menu

import com.example.ponchomovies.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    val route: String
){
    object Home: BottomNavItem(
        title = "Home",
        R.drawable.baseline_home_24,
        route = "moviesHome"
    )

    object Movies: BottomNavItem(
        title = "Movies",
        R.drawable.baseline_movie_24,
        route = "moviesList"
    )

    object Settings: BottomNavItem(
        title = "Settings",
        R.drawable.baseline_settings_24,
        route = "moviesSettings"
    )
}
