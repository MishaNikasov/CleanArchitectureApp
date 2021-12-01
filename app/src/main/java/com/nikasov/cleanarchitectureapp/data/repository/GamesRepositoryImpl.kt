package com.nikasov.cleanarchitectureapp.data.repository

import com.nikasov.cleanarchitectureapp.common.DataState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.GameList
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository

class GamesRepositoryImpl(
    private val networkApi: NetworkApi
): BaseRepository(), GamesRepository {
    override suspend fun getGamesList(): DataState<GameList?> {
        return when (val response = obtainResponse(networkApi.getGamesList())) {
            is DataState.Success -> DataState.successes(response.data?.toGameList())
            is DataState.Error -> DataState.error(response.errorModel)
        }
    }
}