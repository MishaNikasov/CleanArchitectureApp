package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import com.nikasov.cleanarchitectureapp.domain.model.Game

data class GameListState (
    val gameList: List<Game> = arrayListOf()
)