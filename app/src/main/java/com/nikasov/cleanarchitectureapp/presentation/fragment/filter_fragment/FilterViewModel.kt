package com.nikasov.cleanarchitectureapp.presentation.fragment.filter_fragment

import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.domain.usecase.search.GetSearchFiltersUseCase
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    getSearchFiltersUseCase: GetSearchFiltersUseCase
): BaseViewModel() {

    val filterList = getSearchFiltersUseCase().stateIn(
        viewModelScope, SharingStarted.Lazily, State.Empty
    )

}