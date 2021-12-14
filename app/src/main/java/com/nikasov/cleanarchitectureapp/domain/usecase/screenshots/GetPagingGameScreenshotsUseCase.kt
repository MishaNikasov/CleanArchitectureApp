package com.nikasov.cleanarchitectureapp.domain.usecase.screenshots

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingGameScreenshotsUseCase@Inject constructor(
    private val screenshotRepository: ScreenshotRepository
) {

    operator fun invoke(id: String): Flow<PagingData<GameScreenshot>> {
        return screenshotRepository.getPagingGameScreenshots(id)
    }

}