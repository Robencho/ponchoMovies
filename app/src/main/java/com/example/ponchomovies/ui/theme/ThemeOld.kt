package com.example.ponchomovies.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/*private val DarkColorPalette = darkColorScheme(
    primary = primary,
    secondary = secondary,
    background = backGround,
    surface = backgroundView,
    onBackground = onBackground,
    onSurface = backGround,
    error = error
)

private val LightColorPalette = lightColorScheme(
    primary = primary,
    secondary = secondary,
    background = backGround,
    surface = backgroundView,
    onBackground = onBackground,
    onSurface = backGround

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PonchoMoviesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}*/