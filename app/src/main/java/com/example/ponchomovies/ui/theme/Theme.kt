package com.example.ponchomovies.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val LightColors = lightColorScheme(
    primary = color_primary_400,
    secondary = color_primary_800,
    tertiary = color_success_500,
    primaryContainer = color_primary_400,
    secondaryContainer = color_primary_800,
    tertiaryContainer = color_primary_900,
    background = color_primary_400,
    error = color_danger_700,
    errorContainer = color_danger_200,
    surface = color_primary_300,
    onPrimary = color_primary_800,
    onSecondary = color_primary_400,
    onTertiary = color_primary_100,
    onPrimaryContainer = color_primary_900,
    onSecondaryContainer = color_primary_200,
    onTertiaryContainer = color_success_100,
    onBackground = color_primary_700,
    onError = color_danger_200,
    onErrorContainer = color_danger_800,
    onSurface =color_info_800,
)


private val DarkColors = darkColorScheme(
    primary = color_primary_900,
    secondary = color_primary_200,
    tertiary = color_success_100,
    primaryContainer = color_primary_900,
    secondaryContainer = color_primary_200,
    tertiaryContainer = color_success_100,
    background = color_primary_900,
    error = color_danger_200,
    errorContainer = color_danger_800,
    surface = color_primary_700,
    onPrimary = color_primary_200,
    onSecondary = color_primary_700,
    onTertiary = color_primary_900,
    onPrimaryContainer = color_primary_100,
    onSecondaryContainer = color_primary_800,
    onTertiaryContainer = color_success_900,
    onBackground = color_primary_200,
    onError = color_danger_800,
    onErrorContainer = color_danger_200,
    onSurface =color_info_200,
)

@Composable
fun PonchoMoviesTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        shapes = Shapes
    )
}