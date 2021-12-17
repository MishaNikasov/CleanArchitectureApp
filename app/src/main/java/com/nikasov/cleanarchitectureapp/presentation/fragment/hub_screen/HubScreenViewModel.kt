package com.nikasov.cleanarchitectureapp.presentation.fragment.hub_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HubScreenViewModel @Inject constructor(
    getGamesListUseCase: GetGamesListUseCase
) : ViewModel() {

    val gameList = getGamesListUseCase.invoke().cachedIn(viewModelScope)

}