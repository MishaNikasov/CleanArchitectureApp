package com.nikasov.cleanarchitectureapp.common.utils

import kotlinx.coroutines.flow.MutableStateFlow

typealias MutableState<T> = MutableStateFlow<State<T>>

typealias Mapper<Input, Output> = (Input) -> Output

typealias BigList = List<List<List<String>>>

fun ads() {
    val list: BigList = arrayListOf(arrayListOf(arrayListOf("", "")))
}