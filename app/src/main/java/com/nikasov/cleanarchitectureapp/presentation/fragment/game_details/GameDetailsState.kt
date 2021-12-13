package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo

data class GameDetailsState (
    val gameDetails: GameDetails?,
    val infoList: List<GameDetailsInfo>?
)