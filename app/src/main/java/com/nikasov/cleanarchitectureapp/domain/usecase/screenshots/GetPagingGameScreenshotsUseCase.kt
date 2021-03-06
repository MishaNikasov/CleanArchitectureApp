package com.nikasov.cleanarchitectureapp.domain.usecase.screenshots

import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import javax.inject.Inject

class GetPagingGameScreenshotsUseCase@Inject constructor(
    private val screenshotRepository: ScreenshotRepository
) {

    operator fun invoke(id: String) = screenshotRepository.getPagingGameScreenshots(id)

}