package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesListUseCase: GetGamesListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filterQuery = GameListFragmentArgs.fromSavedStateHandle(savedStateHandle).filterQuery

    private var _gameList = MutableStateFlow<PagingData<Game>>(PagingData.empty())
    var gameList = _gameList.asStateFlow()

    init {
        if (filterQuery != null)
            getGameList(filterQuery.asList())
    }

    fun getGameList(filterQuery: List<FilterQuery>) {
        viewModelScope.launch {
            getGamesListUseCase.invoke(filterQuery).collect {
                _gameList.emit(it)
            }
        }
    }
}