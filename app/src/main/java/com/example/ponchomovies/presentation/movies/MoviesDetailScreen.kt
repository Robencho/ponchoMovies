package com.example.ponchomovies.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.utils.PonchoMoviesConstants
import com.example.ponchomovies.R.string
import com.example.ponchomovies.R.drawable
import com.example.ponchomovies.presentation.common.ToolbarScreen
import com.example.ponchomovies.ui.theme.Shapes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesDetailScreen(
    navController: NavController,
    title: String?,
    description: String?,
    imageUrl: String?,
    posterImage: String?,
    releaseDate: String?
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            ToolbarScreen(onIconPressed = {
                navController.popBackStack()
            },
            title = title?: "Holi")
        },
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                val (banner, poster, descriptionDetail, body) = createRefs()
                val topGuide = createGuidelineFromTop(0.2f)

                ContentBannerScreen(imageUrl = imageUrl, modifier = Modifier.constrainAs(
                    banner
                ) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                )
                ContentPosterImage(posterImage = posterImage, modifier = Modifier
                    .constrainAs(
                        poster
                    ) {
                        top.linkTo(topGuide)
                        start.linkTo(parent.start)
                    }
                    .height(130.dp)
                )

                ContentTitleDetail(
                    modifier = Modifier
                        .constrainAs(
                            body
                        ) {
                            top.linkTo(banner.bottom)
                            start.linkTo(poster.end)
                            end.linkTo(parent.end)
                        },
                    title = title,
                    releaseDate = releaseDate,
                )
                Spacer(modifier = Modifier.height(25.dp))
                ContentDescription(
                    description = description,
                    modifier = Modifier
                        .constrainAs(descriptionDetail) {
                            top.linkTo(poster.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                    navController = navController
                )
            }
        }
    )

}

@Composable
fun ContentBannerScreen(imageUrl: String?, modifier: Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = "${PonchoMoviesConstants.URL_IMAGES}/$imageUrl",
            placeholder = painterResource(id = drawable.background_movie),
            contentDescription = "*"
        )
    }

}

@Composable
fun ContentPosterImage(posterImage: String?, modifier: Modifier) {
    AsyncImage(
        model = "${PonchoMoviesConstants.URL_IMAGES}/$posterImage",
        placeholder = painterResource(id = drawable.avatar_place),
        contentDescription = "*",
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxWidth(.35f)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )

}

@Composable
fun ContentTitleDetail(
    title: String?,
    releaseDate: String?,
    modifier: Modifier
) {
    Column(modifier = modifier.fillMaxWidth(.5f)) {
        Text(
            modifier = modifier,
            text = "$title",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = FontFamily.Monospace
            )
        )
        Text(
            modifier = modifier,
            text = releaseDate ?: "",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = FontFamily.Monospace
            )
        )
    }

}

@Composable
fun ContentDescription(description: String?, modifier: Modifier, navController: NavController) {
    Card(
        modifier = modifier,
        shape = Shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = description ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = modifier.padding(start = 16.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(MoviesNavigation.MoviesList.route)
                    },
                    modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(text = stringResource(id = string.btn_on_back))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MoviesDetailPreview() {
    val ctx = LocalContext.current
    MoviesDetailScreen(
        navController = NavController(ctx),
        title = "Holaksbkh",
        description = "sfbsfbsfbsfb",
        imageUrl = "",
        posterImage = "",
        releaseDate = "dfbfbdfb"
    )
}