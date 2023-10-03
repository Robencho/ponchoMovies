package com.example.ponchomovies.core.generic.dto

import com.example.ponchomovies.data.models.remote.dto.response.CastItemDto
import com.google.gson.annotations.SerializedName

class CastDto<T: Any?> {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("cast")
    val cast: List<CastItemDto> = emptyList()
}