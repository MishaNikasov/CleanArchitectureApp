package com.nikasov.cleanarchitectureapp.common.utils

import kotlinx.coroutines.flow.MutableStateFlow

typealias MutableState<T> = MutableStateFlow<State<T>>