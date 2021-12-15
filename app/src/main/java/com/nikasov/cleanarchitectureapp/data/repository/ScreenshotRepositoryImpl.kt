package com.nikasov.cleanarchitectureapp.data.repository

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.data.local.dao.ScreenshotDao
import com.nikasov.cleanarchitectureapp.data.local.entity.ScreenshotEntity
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.remote.util.game.GameScreenshotsPageSource
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScreenshotRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val screenshotDao: ScreenshotDao
) : BaseRepository(), ScreenshotRepository {

    override fun getPagingGameScreenshots(id: String): Flow<PagingData<GameScreenshot>> {
        return GameScreenshotsPageSource(id, networkApi).pager.flow
    }

    override suspend fun getGameScreenshots(id: String): DataState<List<GameScreenshot>> {
        return obtainResponse(
            request = networkApi.getGameScreenshots(id, 1, 10),
            mapper = { gameScreenshotDto ->
                gameScreenshotDto?.results?.map {
                    it.toGameScreenshot(screenshotDao.isFavorite(it.id))
                } ?: arrayListOf()
            }
        )
    }

    override fun getAllSavedScreenshots(): Flow<List<GameScreenshot>> {
        return screenshotDao.getAll().map { list -> list?.map { it.toGameScreenshot() } ?: arrayListOf() }
    }

    override suspend fun saveScreenshot(gameScreenshot: GameScreenshot) {
        screenshotDao.saveScreenshot(ScreenshotEntity(
            gameScreenshot.id,
            gameScreenshot.image
        ))
    }

    override suspend fun removeScreenshot(gameScreenshot: GameScreenshot) {
        screenshotDao.removeScreenshot(gameScreenshot.id)
    }

}