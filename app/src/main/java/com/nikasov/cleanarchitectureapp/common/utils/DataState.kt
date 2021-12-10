package com.nikasov.cleanarchitectureapp.common.utils

sealed class DataState<out R> {

    data class Success<out T>(val data: T?) : DataState<T>()
    data class Error(val errorModel: ErrorModel) : DataState<Nothing>()

    companion object {
        fun <T> successes(data: T?) = Success(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
    }

    fun toState() = when (this) {
        is Success -> State.successes(this.data)
        is Error -> State.error(this.errorModel)
    }
}