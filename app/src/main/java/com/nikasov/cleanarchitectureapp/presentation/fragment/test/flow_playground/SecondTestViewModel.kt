package com.nikasov.cleanarchitectureapp.presentation.fragment.test.flow_playground

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SecondTestViewModel @Inject constructor(): BaseViewModel() {

    data class TestScreenState(
        val firstValue: String?,
        val secondValue: Int?
    )

    private val _firstResponse = MutableStateFlow<State<String?>>(State.Empty)
    private val _secondResponse = MutableStateFlow<State<Int?>>(State.Empty)

    private suspend fun firstRequest(): DataState<String> {
        delay(3000)
        return DataState.successes("First response")
    }

    private suspend fun secondRequest(): DataState<Int> {
        delay(1000)
        return DataState.successes(69)
    }

    fun getData() {
        handleInto(_firstResponse, suspend { firstRequest() })
        handleInto(_secondResponse, suspend { secondRequest() })
    }

//    var screenState = combineTransform(
//        _firstResponse,
//        _secondResponse
//    ) { firstResponse, secondResponse ->
//
//        emit(State.successes(
//            TestScreenState(
//                firstResponse,
//                secondResponse
//            )
//        ))
//
//    }.stateIn(viewModelScope, SharingStarted.Lazily, State.loading())

    val zipScreenState = _firstResponse.zip(_secondResponse) { r1, r2 ->
        State.successes(TestScreenState(r1.getResult(), r2.getResult()))
    }
}