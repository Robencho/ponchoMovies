package com.example.ponchomovies.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.ui.theme.Shapes
import com.example.ponchomovies.R.string

@Composable
fun HomeScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (head, description, homeBody) = createRefs()

        TitleHomeHeadScreen(modifier = Modifier.constrainAs(
            head
        ) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        HomeContentDescriptionScreen(modifier = Modifier.constrainAs(
            description
        ) {
            top.linkTo(head.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        HomeBody(
            modifier = Modifier.constrainAs(homeBody) {
                top.linkTo(description.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, navController
        )
    }
}

@Composable
fun TitleHomeHeadScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.onSecondaryContainer)
        ) {

        }
        Text(
            text = "Movies by Poncho Dev",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Composable
fun HomeContentDescriptionScreen(modifier: Modifier) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = Shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    stringResource(id = string.home_description),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    )
}

@Composable
fun HomeBody(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                navController.navigate(MoviesNavigation.MoviesList.route)
            }
        ) {
            Text(text = "Go To Poncho Movies")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val ctx = LocalContext.current
    PonchoMoviesTheme() {
        HomeScreen(navController = NavController(ctx))
    }
}