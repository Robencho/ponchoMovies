package com.example.ponchomovies.domain.model

data class Cast(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Number = 0,
    val profile_path: String = "",
    val cast_id: Int = 0,
    val character: String = "",
    val credit_id: String = "",
    val order: Int = 0
)
