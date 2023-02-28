package com.example.ponchomovies.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ponchomovies.navigation.MoviesNavigation

@Composable
fun HomeScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        var (homeBody) = createRefs()

        HomeBody(
            modifier = Modifier.constrainAs(homeBody) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }, navController)
    }
}

@Composable
fun HomeBody(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Poncho Movies",
            textAlign = TextAlign.Center
        )
        Button(
            modifier = modifier.fillMaxWidth(),
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

}