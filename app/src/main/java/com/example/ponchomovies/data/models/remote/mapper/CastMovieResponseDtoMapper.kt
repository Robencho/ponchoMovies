package com.example.ponchomovies.data.models.remote.mapper

import com.example.ponchomovies.data.models.remote.dto.response.CastItemDto
import com.example.ponchomovies.data.models.remote.dto.response.CastResponseDto
import com.example.ponchomovies.domain.model.Cast
import com.example.ponchomovies.domain.model.CastResponseEntity

fun CastResponseDto.toCastResponseEntity() = CastResponseEntity(
    id,
    cast
)

fun CastItemDto.mapFromEntity() = Cast(
    adult = this.adult,
    gender = this.gender,
    id = this.id,
    known_for_department = this.known_for_department,
    name = this.name,
    original_name = this.original_name,
    popularity = this.popularity,
    profile_path = this.profile_path.orEmpty(),
    cast_id = this.cast_id,
    character = this.character,
    credit_id = this.credit_id,
    order = this.order
)

fun List<CastItemDto>.mapFromListModel(): List<Cast> {
    return this.map {
        it.mapFromEntity()
    }
}