package com.example.ponchomovies.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ponchomovies.R.drawable
import com.example.ponchomovies.R.string
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.presentation.common.ToolbarScreen
import com.example.ponchomovies.presentation.common.menu.BottomNavigation
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.Shapes
import com.example.ponchomovies.utils.PonchoMoviesConstants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesDetailScreen(
    viewModel: MoviesViewModel,
    navController: NavController,
    movieId: Int,
    title: String?,
    description: String?,
    backgroundPath: String?,
    posterImage: String?,
    releaseDate: String?
) {

    val cast: List<Cast> by viewModel.cast.observeAsState(initial = emptyList())
    viewModel.getCast(movieId = movieId)

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            ToolbarScreen(
                onIconPressed = {
                    navController.popBackStack()
                },
                title = title ?: "Holi"
            )
        },
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                val (banner, casting, descriptionDetail, body) = createRefs()
                ContentBannerScreen(imageUrl = backgroundPath, modifier = Modifier.constrainAs(
                    banner
                ) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                )
                ContentDescription(
                    title = title,
                    releaseDate = releaseDate,
                    description = description,
                    modifier = Modifier
                        .constrainAs(descriptionDetail) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .alpha(0.8f)
                        .padding(top = 180.dp, start = 16.dp, end = 16.dp, bottom = 90.dp),
                    navController = navController,
                    cast = cast
                )
            }
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )

}

@Composable
fun ContentBannerScreen(imageUrl: String?, modifier: Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 70.dp, topEnd = 70.dp
                    )
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data("${PonchoMoviesConstants.URL_IMAGES}/$imageUrl")
                .crossfade(1000)
                .placeholder(drawableResId = drawable.background_movie)
                .build(),
            placeholder = painterResource(id = drawable.background_movie),
            contentScale = ContentScale.Crop,
            contentDescription = "*"
        )
    }

}

@Composable
fun ContentDescription(
    description: String?, title: String?,
    releaseDate: String?, modifier: Modifier, navController: NavController,
    cast: List<Cast>
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = "$title",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Monospace
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = releaseDate ?: "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Monospace
                    ),
                    textAlign = TextAlign.Center
                )
                CasScreen(cast = cast, modifier = Modifier.fillMaxWidth())
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Resumen",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = description ?: "",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            // Todo
                        },
                        Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(text = stringResource(id = string.btn_on_back))
                    }
                }

            }

        }
    )
}

@Composable
fun CasScreen(cast: List<Cast>, modifier: Modifier) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Reparto", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp
            )
        )
    }
    LazyRow(
        content = {
            items(cast.filter { it.profile_path != null }) { itemCast ->
                CastItemScreen(modifier = modifier, castItem = itemCast)
            }
        })
}

@Composable
fun CastItemScreen(modifier: Modifier, castItem: Cast?) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(125.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                val (photoProfile, nameProfile, character) = createRefs()
                AsyncImage(
                    modifier = modifier
                        .constrainAs(photoProfile) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(nameProfile.top)
                        }
                        .size(110.dp)
                        .fillMaxWidth()
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${PonchoMoviesConstants.URL_IMAGES}/${castItem?.profile_path}")
                        .crossfade(1000)
                        .placeholder(drawableResId = drawable.item_cast)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "*"
                )
                Text(
                    modifier = Modifier
                        .constrainAs(nameProfile) {
                            top.linkTo(photoProfile.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(character.top)
                        }
                        .fillMaxWidth(),
                    text = castItem?.name ?: "Error",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    modifier = Modifier
                        .constrainAs(character) {
                            top.linkTo(nameProfile.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxWidth(),
                    text = castItem?.character ?: "Error",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraLight,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MoviesDetailPreview() {
    val ctx = LocalContext.current
    val viewMoDelMock = MoviesViewModel(null, null)
    MoviesDetailScreen(
        viewMoDelMock,
        navController = NavController(ctx),
        movieId = 55678,
        title = "Mi primera vez",
        description = "Movie description",
        backgroundPath = "/7OwsFfoxNGIfWwSmdORyB7v8XNj.jpg",
        posterImage = "/7OwsFfoxNGIfWwSmdORyB7v8XNj.jpg",
        releaseDate = "20/89/89"
    )
}
