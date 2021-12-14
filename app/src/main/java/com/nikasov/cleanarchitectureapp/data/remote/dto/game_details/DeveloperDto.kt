package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem

data class DeveloperDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
) {
    fun toDeveloper() =
        GameDetailsInfoItem.Developer(
            id = id,
            name = name,
            slug = slug
        )
}