package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nikasov.cleanarchitectureapp.common.extensions.addItem
import com.nikasov.cleanarchitectureapp.common.extensions.removeItem
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesListUseCase: GetGamesListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filterQueryFromBundle = GameListFragmentArgs.fromSavedStateHandle(savedStateHandle).filterQuery

    private val _filterQueryList = MutableStateFlow(mutableListOf<FilterQuery>())

    @FlowPreview
    val gameList = _filterQueryList.flatMapConcat { filterList ->
        getGamesListUseCase(filterList).cachedIn(viewModelScope)
    }

    init {
        if (filterQueryFromBundle != null) {
            _filterQueryList.value.addAll(filterQueryFromBundle.asList())
        }
    }

    fun addFilter(filterQuery: FilterQuery) {
        if (filterQuery is FilterQuery.Search) {
            _filterQueryList.value.removeIf { it is FilterQuery.Search }
        }
        _filterQueryList.addItem(filterQuery)
    }

    fun removeFilter(filterQuery: FilterQuery) {
        val index = _filterQueryList.value.indexOfFirst { it.query == filterQuery.query }
        _filterQueryList.removeItem(index)
    }

}