package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.common.DataState
import com.nikasov.cleanarchitectureapp.common.State
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GameUseCases
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gameUseCases: GameUseCases
): ViewModel() {

    private val _state = MutableStateFlow<State<GameListState?>>(State.Empty)
    val state: StateFlow<State<GameListState?>> = _state

    init {
        getGameList()
    }

    private fun getGameList() {
        viewModelScope.launch {
            _state.emit(State.loading())
            when (val gameList = gameUseCases.getGamesListUseCase()) {
                is DataState.Error -> _state.emit(State.error(gameList.errorModel))
                is DataState.Success -> _state.emit(State.successes(
                    GameListState(gameList = gameList.data?.gameList ?: arrayListOf()))
                )
            }
        }
    }

}