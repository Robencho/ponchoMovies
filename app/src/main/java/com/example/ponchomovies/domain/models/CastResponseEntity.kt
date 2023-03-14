package com.example.ponchomovies.domain.models

import com.example.ponchomovies.domain.common.Entity

data class CastResponseEntity(
    val id: Int = 0,
    val cast: List<CastModel> = emptyList()
): Entity()
