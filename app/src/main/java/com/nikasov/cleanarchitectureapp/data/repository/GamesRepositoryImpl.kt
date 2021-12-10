package com.nikasov.cleanarchitectureapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.common.utils.getErrorModel
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.remote.util.game.GamePageSource
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameDetail
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GamesRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): BaseRepository(), GamesRepository {

    override fun getGamesList(): Flow<PagingData<Game>> {
        return GamePageSource(networkApi).pager.flow
    }

    override suspend fun getGameDetail(id: String): DataState<GameDetail> {
        return try {
            val response = networkApi.getGameDetails(id)
            if (response.isSuccessful) {
                val gameDetail = response.body()?.toGameDetail()
                gameDetail?.let {
                    DataState.successes(it)
                } ?: DataState.error(ErrorModel.emptyBodyError())
            } else {
                DataState.error(response.getErrorModel())
            }
        } catch (e: Exception) {
            DataState.error(ErrorModel.getLocalError(e.localizedMessage ?: "Something went wrong"))
        }
    }

}