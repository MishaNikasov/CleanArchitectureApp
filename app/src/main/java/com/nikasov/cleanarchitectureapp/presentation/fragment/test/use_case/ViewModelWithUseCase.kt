package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ViewModelWithUseCase @Inject constructor(
    testUseCase: GetTestScreenUseCase
): BaseViewModel() {

    val screenState = testUseCase(
        additionalString = "Random str"
    ).stateIn(viewModelScope, SharingStarted.Lazily, State.Empty)

    val screenStateLiveData = testUseCase(
        additionalString = "Random str"
    ).asLiveData()

}