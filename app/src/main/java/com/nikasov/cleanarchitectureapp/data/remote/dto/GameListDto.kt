package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.GameList

data class GameListDto(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<GameDto>? = null
) {
    fun toGameList() = GameList(
        count = count ?: -1,
        next = next ?: "",
        previous = previous ?: "",
        gameList = results?.map { it.toGame() } ?: arrayListOf()
    )
}