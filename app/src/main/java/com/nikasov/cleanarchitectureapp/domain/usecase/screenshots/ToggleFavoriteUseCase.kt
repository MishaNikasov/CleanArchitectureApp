package com.nikasov.cleanarchitectureapp.domain.usecase.screenshots

import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val screenshotRepository: ScreenshotRepository
) {

    suspend operator fun invoke(gameScreenshot: GameScreenshot) {
        if (!gameScreenshot.isFavorite) {
            screenshotRepository.saveScreenshot(gameScreenshot)
        } else {
            screenshotRepository.removeScreenshot(gameScreenshot)
        }
    }

}