package com.nikasov.cleanarchitectureapp.common

sealed class DataState<out R> {

    data class Success<out T>(val data: T?) : DataState<T>()
    data class Error(val errorModel: ErrorModel) : DataState<Nothing>()

    fun toState() = when (this) {
        is Success -> State.successes(this.data)
        is Error -> State.error(this.errorModel)
    }
}