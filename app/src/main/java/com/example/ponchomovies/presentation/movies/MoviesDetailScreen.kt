package com.example.ponchomovies.presentation.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.ponchomovies.R
import com.example.ponchomovies.ui.theme.Shapes

@Composable
fun MoviesDetailScreen(
    navController: NavController,
    title: String?,
    description: String?,
    imageUrl: String?,
    posterImage: String?,
    releaseDate: String?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondaryContainer)
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
            .height(200.dp)
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

        ContentDescription(
            description = description,
            modifier = Modifier
                .constrainAs(descriptionDetail) {
                    top.linkTo(poster.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp),
            navController = navController
        )
    }
}

@Composable
fun ContentBannerScreen(imageUrl: String?, modifier: Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = "${PonchoMoviesConstants.URL_IMAGES}/$imageUrl",
            placeholder = painterResource(id = R.drawable.background_movie),
            contentDescription = "*"
        )
    }

}

@Composable
fun ContentPosterImage(posterImage: String?, modifier: Modifier) {
    AsyncImage(
        model = "${PonchoMoviesConstants.URL_IMAGES}/$posterImage",
        placeholder = painterResource(id = R.drawable.avatar_place),
        contentDescription = "*",
        modifier = modifier
            .padding(start = 16.dp)
            .fillMaxWidth(.4f)
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
                color = MaterialTheme.colorScheme.onSecondary,
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
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            ) {
                Text(
                    text = description ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = MaterialTheme.colorScheme.onSecondary
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
                    Text(text = "Volver al Home")
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
        title = "Titulo 1",
        description = "Description 1",
        imageUrl = "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/2ZNFu0hkSVtAI6LRWGIlCPNd1Tj.jpg",
        posterImage = "",
        releaseDate = "2022"
    )
}