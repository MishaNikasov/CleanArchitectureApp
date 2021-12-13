package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

import com.nikasov.cleanarchitectureapp.domain.model.Genre

data class GenreDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
) {
    fun toGenre(): Genre =
        Genre(
            id = id,
            name = name,
            slug = slug
        )
}