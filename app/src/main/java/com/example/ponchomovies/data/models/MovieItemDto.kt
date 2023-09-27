package com.example.ponchomovies.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.ponchomovies.domain.common.DTO
import com.example.ponchomovies.domain.common.Entity
import com.example.ponchomovies.domain.models.MovieItemEntity
import com.example.ponchomovies.domain.models.MovieVideoEntity
import com.example.ponchomovies.domain.models.MovieVideosEntity
import com.google.gson.annotations.SerializedName

@androidx.room.Entity(tableName = "movies_table")
data class MovieItemDto(
    @PrimaryKey(autoGenerate = true) val movie_id: Int? = 0,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String = "",
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String = "",
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Float = 0f,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("videos")
    val videos: MovieVideosDTO = MovieVideosDTO()
) : DTO() {

    companion object {
        val mapToDomain: (DTO) -> Entity = { movieItemDto ->
            movieItemDto as MovieItemDto
            MovieItemEntity(
                movieItemDto.adult,
                movieItemDto.backdropPath,
                movieItemDto.id,
                movieItemDto.originalLanguage,
                movieItemDto.originalTitle,
                movieItemDto.overview,
                movieItemDto.popularity,
                movieItemDto.posterPath,
                movieItemDto.releaseDate,
                movieItemDto.title,
                movieItemDto.video,
                movieItemDto.voteAverage,
                movieItemDto.voteCount,
                movieItemDto.videos.results?.mapListVideosToDomain()?: emptyList()
            )
        }
    }
}

data class MovieVideosDTO(
    @SerializedName("videos")
    val results: List<MovieVideoDto>? = emptyList()
) : DTO() {
    val mapToDomain: (DTO) -> Entity = { movieVideosDto ->
        movieVideosDto as MovieVideosDTO
        MovieVideosEntity(
            results = movieVideosDto.results?.mapListVideosToDomain()?: emptyList()
        )
    }
}

fun List<MovieVideoDto>.mapListVideosToDomain(): List<MovieVideoEntity> {
    val result = mutableListOf<MovieVideoEntity>()
    for (movieVideoItem in this)
        result.add(movieVideoItem.mapToDomain())
    return result
}

fun MovieVideoDto.mapToDomain() = this.map(
    MovieVideoDto.mapToDomain, this
) as MovieVideoEntity? ?: MovieVideoEntity()

data class MovieVideoDto(
    @SerializedName("iso_639_1")
    val iso_639_1: String,
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("id")
    val id: String
) : DTO() {
    companion object {
        val mapToDomain: (DTO) -> Entity = { movieVideosDto ->
            movieVideosDto as MovieVideoDto
            MovieVideoEntity(
                movieVideosDto.iso_639_1,
                movieVideosDto.iso_3166_1,
                movieVideosDto.name,
                movieVideosDto.key,
                movieVideosDto.site,
                movieVideosDto.size,
                movieVideosDto.type,
                movieVideosDto.official,
                movieVideosDto.publishedAt,
                movieVideosDto.id
            )
        }
    }
}

fun List<MovieItemDto>.mapListMovieItemToDomain(): List<MovieItemEntity> {
    val result = mutableListOf<MovieItemEntity>()
    for (movieItem in this)
        result.add(movieItem.mapToDomain())
    return result
}

fun MovieItemDto.mapToDomain() = this.map(
    MovieItemDto.mapToDomain, this
) as MovieItemEntity? ?: MovieItemEntity()
