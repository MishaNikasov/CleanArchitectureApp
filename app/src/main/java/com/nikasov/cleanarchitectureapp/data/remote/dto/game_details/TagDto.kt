package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

import com.nikasov.cleanarchitectureapp.domain.model.Tag

data class TagDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val language: String,
    val name: String,
    val slug: String
) {
    fun toTag() =
        Tag(
            id = id,
            name = name,
            slug = slug,
            gamesCount = games_count
        )
}