package com.example.ponchomovies.data.models.remote.mapper

import com.example.ponchomovies.data.models.remote.dto.response.VideoDto
import com.example.ponchomovies.domain.model.Video

fun VideoDto.mapFromEntity() = Video(
    iso_639_1 = this.iso_639_1,
    iso_3166_1 = this.iso_3166_1,
    name = this.name,
    key = this.key,
    site = this.site,
    size = this.size,
    type = this.type,
    official = this.official,
    published_at = this.published_at,
    id = this.id
)

fun List<VideoDto>.mapFromListModel(): List<Video>{
    return this.map {
        it.mapFromEntity()
    }
}