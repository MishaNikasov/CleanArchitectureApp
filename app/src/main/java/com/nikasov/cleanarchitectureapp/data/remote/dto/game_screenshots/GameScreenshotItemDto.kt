package com.nikasov.cleanarchitectureapp.data.remote.dto.game_screenshots

import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot

data class GameScreenshotItemDto(
    val height: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val is_deleted: Boolean? = null,
    val width: Int? = null
) {
    fun toGameScreenshot() =
        GameScreenshot(
            height = height ?: 0,
            id = id ?: 0,
            image = image ?: "",
            width = width ?: 0
        )
}