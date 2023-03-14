package com.example.ponchomovies.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import coil.request.ImageRequest
import com.example.ponchomovies.navigation.MoviesNavigation
import com.example.ponchomovies.utils.PonchoMoviesConstants
import com.example.ponchomovies.R.string
import com.example.ponchomovies.R.drawable
import com.example.ponchomovies.domain.models.CastModel
import com.example.ponchomovies.domain.models.MovieResponse
import com.example.ponchomovies.domain.usecase.GetCastUseCase
import com.example.ponchomovies.presentation.common.ToolbarScreen
import com.example.ponchomovies.presentation.movies.viewmodel.MoviesViewModel
import com.example.ponchomovies.ui.theme.Shapes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesDetailScreen(
    viewModel: MoviesViewModel,
    navController: NavController,
    movieId: String,
    title: String?,
    description: String?,
    backgroundPath: String?,
    posterImage: String?,
    releaseDate: String?
) {

    val cast: List<CastModel> by viewModel.cast.observeAsState(initial = emptyList())

    if (cast.isEmpty())
        viewModel.getCast(movieId = movieId)

    Scaffold(
        modifier = Modifier,
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
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                val (banner, casting, descriptionDetail, body) = createRefs()
                val topGuide = createGuidelineFromTop(0.22f)

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
                            top.linkTo(topGuide)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    navController = navController,
                    cast = cast
                )
            }
        }
    )

}

@Composable
fun ContentBannerScreen(imageUrl: String?, modifier: Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 24.dp, bottomEnd = 24.dp
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
fun CasScreen(cast: List<CastModel>, modifier: Modifier) {
    LazyRow(content = {
        items(cast.filter { it.profile_path != null }) { itemCast ->
            CastItemScreen(modifier = modifier, castItem = itemCast)
        }
    })
}

@Composable
fun CastItemScreen(modifier: Modifier, castItem: CastModel?) {
    Card(
        modifier = modifier.height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(12.dp),
        content = {
            Column(modifier = modifier.padding(5.dp)) {
                AsyncImage(
                    modifier = modifier
                        .width(125.dp)
                        .height(125.dp)
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
                    text = castItem?.name ?: "Error",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
                Text(
                    text = castItem?.character ?: "Error",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraLight,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }
    )
}

@Composable
fun ContentDescription(
    description: String?, title: String?,
    releaseDate: String?, modifier: Modifier, navController: NavController,
    cast: List<CastModel>
) {
    Card(
        modifier = modifier.padding(bottom = 50.dp),
        shape = Shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        content = {
            LazyColumn(
                modifier = modifier
            ) {
                item {
                    Column(modifier = modifier.fillMaxWidth(.5f)) {
                        Text(
                            modifier = modifier,
                            text = "$title",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontFamily = FontFamily.Monospace
                            )
                        )
                        Text(
                            modifier = modifier,
                            text = releaseDate ?: "",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontFamily = FontFamily.Monospace
                            )
                        )
                    }

                    CasScreen(cast = cast, modifier = modifier)

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
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MoviesDetailPreview() {
    val ctx = LocalContext.current
    //CastItemScreen(modifier = Modifier, castItem = CastModel())
    MoviesDetailScreen(
        MoviesViewModel(null, null),
        navController = NavController(ctx),
        movieId = "",
        title = "",
        description = "",
        backgroundPath = "",
        posterImage = "",
        releaseDate = ""
    )
}