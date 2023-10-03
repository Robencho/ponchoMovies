package com.example.ponchomovies.domain.model

import com.example.ponchomovies.data.models.remote.dto.response.CastItemDto

data class CastResponseEntity(
    val id: Int = 0,
    val cast: List<CastItemDto> = emptyList()
)
