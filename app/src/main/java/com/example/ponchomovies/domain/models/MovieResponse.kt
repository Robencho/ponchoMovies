package com.example.ponchomovies.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_table")
data class MovieResponse(
    @PrimaryKey(autoGenerate = true) val movie_id:Int? = 0,

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
    val title: String= "",
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Float = 0f,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int = 0
)
