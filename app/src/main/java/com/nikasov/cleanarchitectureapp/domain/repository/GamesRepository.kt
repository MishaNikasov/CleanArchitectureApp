package com.nikasov.cleanarchitectureapp.domain.repository

import com.nikasov.cleanarchitectureapp.common.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameList

interface GamesRepository {
    suspend fun getGamesList(): DataState<GameList?>
}