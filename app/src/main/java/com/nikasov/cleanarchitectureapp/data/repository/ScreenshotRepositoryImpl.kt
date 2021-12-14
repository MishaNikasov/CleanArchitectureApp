package com.nikasov.cleanarchitectureapp.data.repository

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.remote.util.game.GamePageSource
import com.nikasov.cleanarchitectureapp.data.remote.util.game.GameScreenshotsPageSource
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScreenshotRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : BaseRepository(), ScreenshotRepository {

    override fun getPagingGameScreenshots(id: String): Flow<PagingData<GameScreenshot>> {
        return GameScreenshotsPageSource(id, networkApi).pager.flow
    }

    override suspend fun getGameScreenshots(id: String): DataState<List<GameScreenshot>> {
        return obtainResponse(
            request = networkApi.getGameScreenshots(id, 1, 10),
            mapper = { gameScreenshotDto ->
                gameScreenshotDto?.results?.map { it.toGameScreenshot() } ?: arrayListOf()
            }
        )
    }

}