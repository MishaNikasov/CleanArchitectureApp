package com.nikasov.cleanarchitectureapp.data.remote.dto.game_screenshots

import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot

data class GameScreenshotItemDto(
    val height: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val is_deleted: Boolean? = null,
    val width: Int? = null
) {
    fun toGameScreenshot(isFavorite: Boolean = false) =
        GameScreenshot(
            id = id ?: 0,
            image = image ?: "",
            isFavorite = isFavorite
        )
}