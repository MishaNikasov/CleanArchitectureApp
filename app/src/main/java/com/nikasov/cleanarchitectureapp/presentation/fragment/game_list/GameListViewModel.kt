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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SEARCH_DELAY = 700L

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesListUseCase: GetGamesListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filterQueryFromBundle = GameListFragmentArgs.fromSavedStateHandle(savedStateHandle).filterQuery

    private val _filterQueryList = MutableStateFlow(mutableListOf<FilterQuery>())

    val gameList = _filterQueryList.flatMapLatest { filterList ->
        delay(SEARCH_DELAY)
        getGamesListUseCase(filterList)
    }.cachedIn(viewModelScope)

    init {
        if (filterQueryFromBundle != null) {
            _filterQueryList.value.addAll(filterQueryFromBundle.asList())
        }
    }

    fun search(text: String) {
        _filterQueryList.value.removeIf { it is FilterQuery.Search }
        addFilter(FilterQuery.Search(text))
    }

    fun addFilter(filterQuery: FilterQuery) {
        viewModelScope.launch {
            _filterQueryList.addItem(filterQuery)
        }
    }

    fun removeFilter(filterQuery: FilterQuery) {
        viewModelScope.launch {
            val index = _filterQueryList.value.indexOfFirst { it.query == filterQuery.query }
            _filterQueryList.removeItem(index)
        }
    }

}