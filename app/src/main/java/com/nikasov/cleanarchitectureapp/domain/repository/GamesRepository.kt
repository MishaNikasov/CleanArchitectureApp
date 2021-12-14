package com.nikasov.cleanarchitectureapp.domain.repository

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGamesList(): Flow<PagingData<Game>>
    fun getGameScreenshots(id: String): Flow<PagingData<GameScreenshot>>
    suspend fun getGameDetail(id: String): DataState<GameDetails>
}