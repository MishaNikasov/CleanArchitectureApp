package com.nikasov.cleanarchitectureapp.data.repository

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.remote.util.game.GamePageSource
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : BaseRepository(), GamesRepository {

    override fun getGamesList(): Flow<PagingData<Game>> {
        return GamePageSource(networkApi).pager.flow
    }

    override suspend fun getGameDetail(id: String): DataState<GameDetails> {
        return obtainResponse(
            block = networkApi.getGameDetails(id),
            mapper = {
                it?.toGameDetail()
            }
        )
    }
}