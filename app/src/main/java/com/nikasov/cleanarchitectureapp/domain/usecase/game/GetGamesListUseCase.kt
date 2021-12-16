package com.nikasov.cleanarchitectureapp.domain.usecase.game

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameListQuery
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesListUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {
    operator fun invoke(gameListQuery: GameListQuery = GameListQuery()): Flow<PagingData<Game>> {
        return gamesRepository.getGamesList(gameListQuery)
    }
}