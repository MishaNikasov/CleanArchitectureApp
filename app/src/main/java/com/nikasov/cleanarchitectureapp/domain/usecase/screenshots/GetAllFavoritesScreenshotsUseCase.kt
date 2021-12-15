package com.nikasov.cleanarchitectureapp.domain.usecase.screenshots

import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import javax.inject.Inject

class GetAllFavoritesScreenshotsUseCase @Inject constructor(
    private val screenshotRepository: ScreenshotRepository
){

    operator fun invoke() = screenshotRepository.getAllSavedScreenshots()

}