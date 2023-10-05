package com.example.ponchomovies.data.models.remote.dto.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDto(
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
    @SerializedName("published_at")
    val published_at: String,
    @SerializedName("id")
    val id: String,
) : Serializable
