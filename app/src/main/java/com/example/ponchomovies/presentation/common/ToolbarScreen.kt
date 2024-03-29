package com.example.ponchomovies.presentation.common

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ponchomovies.R
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarScreen(
    onIconPressed: (() -> Unit),
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
            .alpha(0.8f),
        navigationIcon = {
            IconButton(
                onClick = { onIconPressed() },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                })
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        )
    )
}

@Preview
@Composable
fun TopBarPreview() {
    PonchoMoviesTheme() {
        ToolbarScreen(onIconPressed = {}, "Hola")
    }
}