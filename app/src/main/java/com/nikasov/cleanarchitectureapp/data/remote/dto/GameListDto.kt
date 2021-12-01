package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.GameList

data class GameListDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<GameDto>
) {
    fun toGameList() = GameList(
        count = count,
        next = next,
        previous = previous,
        gameList = results.map { it.toGame() }
    )
}