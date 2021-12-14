package com.nikasov.cleanarchitectureapp.domain.usecase.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import javax.inject.Inject

class GetGameDetailsInfoListUseCase @Inject constructor() {

    operator fun invoke(gameDetails: GameDetails?): List<GameDetailsInfo> {
        gameDetails ?: return arrayListOf()

        val infoItems = arrayListOf<GameDetailsInfo>()

        gameDetails.apply {
            genres?.let { genres ->
                infoItems.add(
                    GameDetailsInfo.Genres(genres)
                )
            }
            developers?.let { devs ->
                infoItems.add(
                    GameDetailsInfo.Developers(devs)
                )
            }
            tags?.let { tags ->
                infoItems.add(
                    GameDetailsInfo.Tags(tags)
                )
            }
        }

        return infoItems
    }

}