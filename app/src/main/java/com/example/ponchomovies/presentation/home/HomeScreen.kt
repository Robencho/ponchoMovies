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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val switchState by remember { moviesViewModel.isDarkThemeEnabled }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (switch, head, description, homeBody) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        val topGuideToContent = createGuidelineFromTop(0.3f)
        val bottomGuide = createGuidelineFromBottom(0.1f)
        EnabledDarkTheme(
            modifier = Modifier
                .constrainAs(
                    switch
                ) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 17.dp),
            switchState,
            moviesViewModel
        )
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
private fun EnabledDarkTheme(
    modifier: Modifier,
    checkedListener: Boolean,
    moviesViewModel: MoviesViewModel
) {
    Switch(
        modifier = modifier,
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

@Composable
fun TitleHomeHeadScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = string.title_home),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.Monospace,
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
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(
                    modifier = modifier
                        .weight(0.5f)
                        .width(170.dp)
                        .height(200.dp),
                    painter = painterResource(id = drawable.kotlin),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
                Text(
                    stringResource(id = string.home_description),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Monospace
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
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
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
        HomeScreen(navController = NavController(ctx), MoviesViewModel(null, null))
    }
}