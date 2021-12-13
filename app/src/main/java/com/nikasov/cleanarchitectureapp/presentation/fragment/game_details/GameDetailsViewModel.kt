package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.lifecycle.SavedStateHandle
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.domain.usecase.game_details.GetGameDetailsInfoListUseCase
import com.nikasov.cleanarchitectureapp.domain.usecase.game_details.GetGameDetailsUseCase
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val getGameDetailsInfoListUseCase: GetGameDetailsInfoListUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _gameDetailState = MutableStateFlow<State<GameDetailsState>>(State.loading())
    val gameDetailState = _gameDetailState.asStateFlow()

    private val _gameId = savedStateHandle.get<String>("gameId")

    fun getGameDetail() {
        handleInto(
            stateFlow = _gameDetailState,
            block = suspend { getGameDetailsUseCase(_gameId ?: "") },
            mapper = { gameDetails ->
                GameDetailsState(
                    gameDetails,
                    getGameDetailsInfoListUseCase(gameDetails)
                )
            }
        )
    }

}