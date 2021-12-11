package com.nikasov.cleanarchitectureapp.domain.usecase.game

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
){
    suspend operator fun invoke(gameId: String): DataState<GameDetails> {
        return gamesRepository.getGameDetail(gameId)
    }
}