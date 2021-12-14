package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem

data class ParentPlatformItemDto(
    val id: Int,
    val name: String,
    val slug: String
) {
    fun toParentPlatform()=
        GameDetailsInfoItem.ParentPlatform(
            id = id,
            name = name,
            slug = slug
        )
}