package com.example.ponchomovies.presentation.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.R

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
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        modifier = Modifier,
        navigationIcon = {
            IconButton(
                onClick = { onIconPressed() },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                })
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
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