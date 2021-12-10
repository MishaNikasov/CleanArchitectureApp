package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    getGamesListUseCase: GetGamesListUseCase
) : ViewModel() {

    var gameList = getGamesListUseCase().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

//    private val _state = MutableStateFlow<State<GameListState>>(State.Empty)
//    val state = _state.asStateFlow()
//
//    fun getGameList() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _state.emit(State.loading())
//
//            when (val gameList = gameUseCases.getGamesListUseCase(
//                pageNumber,
//                pageSize
//            )) {
//                is DataState.Error -> _state.emit(State.error(gameList.errorModel))
//                is DataState.Success -> {
//                    pageNumber++
//                    _state.emit(
//                        State.successes(
//                            GameListState(gameList = gameList.data ?: arrayListOf())
//                        )
//                    )
//                }
//            }
//        }
//    }

}