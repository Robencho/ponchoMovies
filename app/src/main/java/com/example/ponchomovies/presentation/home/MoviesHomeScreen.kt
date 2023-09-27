package com.example.ponchomovies.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ponchomovies.R
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.presentation.common.menu.BottomNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesHomeScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val switchState by remember { moviesViewModel.isDarkThemeEnabled }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(36.dp)
            ) {
                val (topContent, bodyContent, bottomContent) = createRefs()

                TitleHomeHeadScreen(modifier = Modifier.constrainAs(topContent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(bodyContent) {
                            top.linkTo(topContent.bottom)
                            bottom.linkTo(parent.bottom)
                        },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        navController.navigate(MoviesNavigation.MoviesList.route)
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_home_go_to_movies))
                }
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )
}

@Composable
fun TitleHomeHeadScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.title_home),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MoviesHomeScreenPreview() {
    val ctx = LocalContext.current
    PonchoMoviesTheme() {
        MoviesHomeScreen(navController = NavController(ctx), MoviesViewModel(null, null))
    }
}