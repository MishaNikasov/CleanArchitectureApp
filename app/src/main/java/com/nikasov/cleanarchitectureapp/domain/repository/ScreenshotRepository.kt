package com.nikasov.cleanarchitectureapp.domain.repository

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import kotlinx.coroutines.flow.Flow

interface ScreenshotRepository  {
    fun getPagingGameScreenshots(id: String): Flow<PagingData<GameScreenshot>>
    suspend fun getGameScreenshots(id: String): DataState<List<GameScreenshot>>
}