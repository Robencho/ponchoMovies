package com.example.ponchomovies.data.models.remote.mapper

import com.example.ponchomovies.data.models.remote.dto.response.MovieResponseDto
import com.example.ponchomovies.domain.model.Movie

fun MovieResponseDto.mapFromEntity() = Movie(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)
fun List<MovieResponseDto>.mapFromListModel(): List<Movie> {
    return this.map {
        it.mapFromEntity()
    }
}
