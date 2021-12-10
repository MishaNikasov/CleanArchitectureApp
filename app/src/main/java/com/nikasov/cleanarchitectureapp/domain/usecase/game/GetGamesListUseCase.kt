package com.nikasov.cleanarchitectureapp.domain.usecase.game

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameList
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import javax.inject.Inject

class GetGamesListUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(): DataState<GameList?> {
        return gamesRepository.getGamesList()
    }
}