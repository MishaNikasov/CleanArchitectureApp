package com.nikasov.cleanarchitectureapp.presentation.fragment.test.flow_playground

import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): BaseViewModel() {

    data class TestScreenState(
        val firstValue: String?,
        val secondValue: String?
    )

    private val _firstResponse = MutableStateFlow<State<String?>>(State.Empty)
    private val _secondResponse = MutableStateFlow<State<String?>>(State.Empty)

    private suspend fun firstRequest(): DataState<String> {
        delay(2000)
        return DataState.successes("First response")
    }

    private suspend fun secondRequest(): DataState<String> {
        delay(4000)
        return DataState.successes("Second response")
    }

    fun getData() {
        handleInto(_firstResponse, suspend { firstRequest() })
        handleInto(_secondResponse, suspend { secondRequest() })
    }

    fun getDataWithOneFailedRequest() {
        handleInto(_firstResponse, suspend { firstRequest() })
        handleInto(_secondResponse, suspend { secondRequest() })
    }

    var screenState = combineTransform(
        _firstResponse,
        _secondResponse
    ) { firstResponse, secondResponse ->

        val firstObtainedResult = when (firstResponse) {
            is State.Successes -> firstResponse.data
            is State.Error -> null
            else -> return@combineTransform
        }

        val secondObtainedResult = when (secondResponse) {
            is State.Successes -> secondResponse.data
            is State.Error -> null
            else-> return@combineTransform
        }

        emit(State.successes(
            TestScreenState(
                firstObtainedResult,
                secondObtainedResult
            )
        ))

    }.stateIn(viewModelScope, SharingStarted.Lazily, State.loading())

    /**
     *
     *
     *  var flow1 = flow {
     *      delay(2000)
     *      emit(1)
     *  }
     *
     *  var flow2 = flow {
     *      delay(4000)
     *      emit(2)
     *  }
     *
     *  var combined: Flow<State<Pair<Int, Int>>> = combineTransform(
     *      flow1,
     *      flow2
     *  ) { i1, i2 ->
     *      emit(State.loading())
     *      emit(State.successes(Pair(i1, i2)))
     *  }
     *
     */


}