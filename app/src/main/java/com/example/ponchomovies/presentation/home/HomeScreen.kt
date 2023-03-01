package com.example.ponchomovies.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.ui.theme.Shapes
import com.example.ponchomovies.R.string
import com.example.ponchomovies.R.drawable

@Composable
fun HomeScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        val (head, description, homeBody) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        val topGuideToContent = createGuidelineFromTop(0.3f)
        val bottomGuide = createGuidelineFromBottom(0.1f)
        TitleHomeHeadScreen(modifier = Modifier.constrainAs(
            head
        ) {
            top.linkTo(topGuide)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        HomeContentDescriptionScreen(modifier = Modifier.constrainAs(
            description
        ) {
            top.linkTo(topGuideToContent)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        HomeBody(
            modifier = Modifier.constrainAs(homeBody) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomGuide)
            }, navController
        )
    }
}

@Composable
fun TitleHomeHeadScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = string.title_home),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = FontFamily.Cursive,
                fontSize = 18.sp
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
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(
                    modifier = modifier
                        .weight(0.5f)
                        .width(170.dp)
                        .height(200.dp),
                    painter = painterResource(id = drawable.photo_profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
                Text(
                    stringResource(id = string.home_description),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = FontFamily.Cursive
                    ),
                    modifier = modifier
                        .weight(0.3f)
                        .padding(8.dp)
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
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = {
                navController.navigate(MoviesNavigation.MoviesList.route)
            }
        ) {
            Text(text = stringResource(id = string.btn_home_go_to_movies))
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