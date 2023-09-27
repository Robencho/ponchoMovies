package com.example.ponchomovies.presentation.common.menu

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Movies,
        BottomNavItem.Settings
    )

    NavigationBar() {
        items.forEach { item ->
            AddItem(screen = item, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(screen: BottomNavItem, navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(painterResource(id = screen.icon), contentDescription = screen.title)
        },
        selected = screen.route == backStackEntry.value?.destination?.route,
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route)
        },
        colors = NavigationBarItemDefaults.colors()
    )
}