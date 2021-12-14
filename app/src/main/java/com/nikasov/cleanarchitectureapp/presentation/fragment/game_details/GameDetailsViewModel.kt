package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.common.utils.getCommonErrorModel
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.usecase.game_details.GetGameDetailsInfoListUseCase
import com.nikasov.cleanarchitectureapp.domain.usecase.game_details.GetGameDetailsUseCase
import com.nikasov.cleanarchitectureapp.domain.usecase.screenshots.GetGameScreenshotsUseCase
import com.nikasov.cleanarchitectureapp.domain.usecase.screenshots.GetPagingGameScreenshotsUseCase
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val getGameDetailsInfoListUseCase: GetGameDetailsInfoListUseCase,
    private val getGameScreenshotsUseCase: GetGameScreenshotsUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _gameId = savedStateHandle.get<String>("gameId") ?: ""

    private val _gameDetailState = MutableStateFlow<State<GameDetailsState>>(State.loading())
    val gameDetailState = _gameDetailState.asStateFlow()

    fun getGameDetail() {
        viewModelScope.launch(Dispatchers.IO) {

            _gameDetailState.emit(State.loading())

            var gameDetails: GameDetails? = null
            var gameScreenshots: List<GameScreenshot> = arrayListOf()

            val errorList = arrayListOf<ErrorModel>()

            listOf(
                async { getGameDetailsUseCase(_gameId).getResult(
                    successesBlock = { details ->
                        gameDetails = details
                    },
                    errorBlock = {
                        errorList.add(it)
                    }
                ) },
                async { getGameScreenshotsUseCase(_gameId).getResult(
                    successesBlock = { list ->
                        gameScreenshots = list ?: arrayListOf()
                    },
                    errorBlock = {
                        errorList.add(it)
                    }
                ) },
            ).awaitAll()

            if (errorList.isEmpty()) {
                _gameDetailState.emit(
                    State.successes(
                        GameDetailsState(
                            gameDetails,
                            getGameDetailsInfoListUseCase(gameDetails),
                            gameScreenshots
                    )
                ))
            } else {
                _gameDetailState.emit(
                    State.error(errorList.getCommonErrorModel())
                )
            }
        }
    }

}