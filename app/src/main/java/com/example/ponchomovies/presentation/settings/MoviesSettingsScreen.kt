package com.example.ponchomovies.presentation.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ponchomovies.R
import com.example.ponchomovies.presentation.common.menu.BottomNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesSettingsScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val switchState by remember { moviesViewModel.isDarkThemeEnabled }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {},
        content = {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (contentEnableDarkTheme) = createRefs()

                EnabledDarkTheme(
                    modifier = Modifier.constrainAs(contentEnableDarkTheme) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    checkedListener = switchState,
                    moviesViewModel = moviesViewModel
                )
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
}

@Composable
private fun EnabledDarkTheme(
    modifier: Modifier,
    checkedListener: Boolean,
    moviesViewModel: MoviesViewModel
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val (enableDarkThemeText, enableDarkThemeSwitch) = createRefs()

        Text(
            text = stringResource(id = R.string.enabled_dark_theme_text),
            modifier = Modifier.constrainAs(enableDarkThemeText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(enableDarkThemeSwitch.start)
                bottom.linkTo(parent.bottom)
            })

        Switch(
            modifier = Modifier.constrainAs(enableDarkThemeSwitch) {
                top.linkTo(parent.top)
                start.linkTo(enableDarkThemeText.end)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            checked = checkedListener,
            onCheckedChange = {
                moviesViewModel.setTheme(!checkedListener)
            },
            thumbContent = {
                Icon(
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                    imageVector = if (checkedListener) Icons.Rounded.Build else Icons.Rounded.CheckCircle,
                    contentDescription = "Switch Icon"
                )

            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.tertiary,
                checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.onPrimary
            ),
        )
    }
}

/*
@Composable
@Preview(showBackground = true)
fun MoviesSettingsScreenPreview() {
    val ctx = LocalContext.current
    PonchoMoviesTheme() {
        MoviesSettingsScreen(navController = NavController(ctx), MoviesViewModel(null, null))
    }
}*/
