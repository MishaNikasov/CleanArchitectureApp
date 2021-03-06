package com.nikasov.cleanarchitectureapp.data.remote.dto.filter

import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem

data class FilterDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
){
    fun toGenre() =
        GameDetailsInfoItem.Genre(
            id = id,
            name = name,
            slug = slug
        )

    fun toDeveloper() =
        GameDetailsInfoItem.Developer(
            id = id,
            name = name,
            slug = slug
        )

    fun toTag() =
        GameDetailsInfoItem.Tag(
            id = id,
            name = name,
            slug = slug,
            gamesCount = games_count
        )
}