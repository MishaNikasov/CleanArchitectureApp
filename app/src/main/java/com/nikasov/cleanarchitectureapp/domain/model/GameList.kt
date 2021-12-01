package com.nikasov.cleanarchitectureapp.domain.model

data class GameList(
    val count: Int,
    val next: String,
    val previous: String,
    val gameList: List<Game>
) 