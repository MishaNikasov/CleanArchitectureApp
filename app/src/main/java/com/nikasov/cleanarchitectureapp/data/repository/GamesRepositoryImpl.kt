package com.nikasov.cleanarchitectureapp.data.repository

import com.nikasov.cleanarchitectureapp.common.DataState
import com.nikasov.cleanarchitectureapp.common.ErrorModel
import com.nikasov.cleanarchitectureapp.common.getErrorModel
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.GameList
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): BaseRepository(), GamesRepository {
    override suspend fun getGamesList(): DataState<GameList?> {
        return try {
            val response = networkApi.getGamesList()
            if (response.isSuccessful) {
                DataState.successes(response.body()?.toGameList())
            } else {
                DataState.error(response.getErrorModel())
            }
        } catch (e: Exception) {
            DataState.error(ErrorModel.getLocalError(e.localizedMessage ?: "Something went wrong"))
        }
    }
//    override suspend fun getGamesList(): DataState<GameList?> {
//        return when (val response = obtainResponse(networkApi.getGamesList())) {
//            is DataState.Success -> DataState.successes(response.data?.toGameList())
//            is DataState.Error -> DataState.error(response.errorModel)
//        }
//    }
}