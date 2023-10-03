package com.example.ponchomovies.data.models.remote.dto.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CastResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<CastItemDto>
) : Serializable

data class CastItemDto(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for_department")
    val known_for_department: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val original_name: String,
    @SerializedName("popularity")
    val popularity: Number,
    @SerializedName("profile_path")
    val profile_path: String?,
    @SerializedName("cast_id")
    val cast_id: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("credit_id")
    val credit_id: String,
    @SerializedName("order")
    val order: Int
) : Serializable
