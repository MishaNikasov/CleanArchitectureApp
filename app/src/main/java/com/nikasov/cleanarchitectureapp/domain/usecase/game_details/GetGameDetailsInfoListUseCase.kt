package com.nikasov.cleanarchitectureapp.domain.usecase.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import javax.inject.Inject

class GetGameDetailsInfoListUseCase @Inject constructor() {

    operator fun invoke(gameDetails: GameDetails?): List<GameDetailsInfo> {
        gameDetails ?: return arrayListOf()

        val infoItems = arrayListOf<GameDetailsInfo>()

        gameDetails.apply {
            genres?.let {
                infoItems.add(
                    GameDetailsInfo.Genres(it.joinToString { item -> item.name })
                )
            }
            developers?.let {
                infoItems.add(
                    GameDetailsInfo.Developers(it.joinToString { item -> item.name })
                )
            }
            tags?.let {
                infoItems.add(
                    GameDetailsInfo.Tags(it.joinToString { item -> item.name })
                )
            }
        }

        return infoItems
    }

}