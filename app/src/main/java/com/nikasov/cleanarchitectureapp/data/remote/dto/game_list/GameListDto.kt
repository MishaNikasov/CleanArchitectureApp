package com.nikasov.cleanarchitectureapp.data.remote.dto.game_list

data class GameListDto(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<GameDto>? = null
)