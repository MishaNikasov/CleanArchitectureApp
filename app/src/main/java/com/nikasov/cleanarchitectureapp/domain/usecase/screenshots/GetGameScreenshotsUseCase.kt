package com.nikasov.cleanarchitectureapp.domain.usecase.screenshots

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import javax.inject.Inject

class GetGameScreenshotsUseCase@Inject constructor(
    private val screenshotRepository: ScreenshotRepository
) {

    suspend operator fun invoke(id: String): DataState<List<GameScreenshot>> {
        return screenshotRepository.getGameScreenshots(id)
    }

}