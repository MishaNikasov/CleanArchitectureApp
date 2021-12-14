package com.nikasov.cleanarchitectureapp.data.remote.dto.game_screenshots

data class GameScreenshotsDto(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<GameScreenshotItemDto>? = null
)