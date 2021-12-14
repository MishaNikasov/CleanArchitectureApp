package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    getGamesListUseCase: GetGamesListUseCase
) : ViewModel() {

    val gameList = getGamesListUseCase().cachedIn(viewModelScope)

}