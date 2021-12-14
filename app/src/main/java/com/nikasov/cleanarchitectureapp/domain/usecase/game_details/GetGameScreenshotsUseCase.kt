package com.nikasov.cleanarchitectureapp.domain.usecase.game_details

import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameScreenshotsUseCase@Inject constructor(
    private val gamesRepository: GamesRepository
) {

    operator fun invoke(id: String): Flow<PagingData<GameScreenshot>> {
        return gamesRepository.getGameScreenshots(id)
    }

}