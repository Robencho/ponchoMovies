package com.example.ponchomovies.presentation.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ponchomovies.R
import com.example.ponchomovies.data.models.MovieItemDto
import com.example.ponchomovies.domain.models.MovieItemEntity
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.ui.theme.PonchoMoviesTheme
import com.example.ponchomovies.utils.PonchoMoviesConstants

@Composable
fun MoviesItemScreen(moviesEntity: MovieItemEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = MaterialTheme.shapes.extraLarge,
        content = {
            Column(
                modifier =
                Modifier
                    .clickable {
                        navController.navigate(
                            MoviesNavigation.MovieDetail.createRoute(
                                moviesEntity.originalTitle,
                                moviesEntity.overview,
                                moviesEntity.backdropPath,
                                moviesEntity.posterPath,
                                moviesEntity.releaseDate,
                                moviesEntity.id.toString()
                            )
                        )
                    }
                    .background(colorScheme.surface)
                    .padding(bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${PonchoMoviesConstants.URL_IMAGES}/${moviesEntity.posterPath}")
                            .crossfade(1000)
                            .placeholder(drawableResId = R.drawable.avatar_place)
                            .build(),
                        contentDescription = "*",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )
                    ProgressCountAverage(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                        moviesEntity.voteAverage.toString()
                    )
                }
                Text(
                    text = moviesEntity.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onSurface,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = moviesEntity.releaseDate,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        color = colorScheme.onSurface,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    )
}

@Composable
fun ProgressCountAverage(modifier: Modifier, percent: String) {
    val percentOk = percent.toFloat()
    val new = percentOk / 10
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(50.dp)
            .background(colorScheme.surface)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = new,
            color = if (new < 0.7f) Color.Yellow else Color.Green,
            strokeWidth = 7.dp
        )
        Text(
            text = "$percent",
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                color = colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMoviesItem() {
    val ctx = LocalContext.current
    PonchoMoviesTheme(false) {
        MoviesItemScreen(
            moviesEntity = MovieItemEntity(
                voteAverage = 9.0f
            ),
            navController = NavController(ctx)
        )
    }
}