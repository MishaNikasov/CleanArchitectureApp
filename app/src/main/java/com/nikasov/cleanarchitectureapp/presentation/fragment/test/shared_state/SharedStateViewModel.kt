package com.nikasov.cleanarchitectureapp.presentation.fragment.test.shared_state

import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SharedStateViewModel: BaseViewModel() {

    private var value: Int = 0

    val counter = MutableSharedFlow<Int>()
    val completedValue = MutableSharedFlow<Int>(3)

    init {
        viewModelScope.launch {
            counter
                .map {
                    it*10
                }
                .collect {
                    delay(1000)
                    completedValue.emit(it)
                }
        }
    }

    fun putValue() {
        viewModelScope.launch {
            value++
            counter.emit(value)
        }
    }

}