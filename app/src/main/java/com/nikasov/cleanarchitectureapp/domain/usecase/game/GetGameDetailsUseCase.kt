package com.nikasov.cleanarchitectureapp.domain.usecase.game

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameDetail
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
){
    suspend operator fun invoke(gameId: String): DataState<GameDetail> {
        return gamesRepository.getGameDetail(gameId)
    }
}