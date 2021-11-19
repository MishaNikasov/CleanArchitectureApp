package com.nikasov.cleanarchitectureapp.common

sealed class State<out T> {

    data class Successes<out T>(val data: T) : State<T>()
    data class Error(val errorModel: ErrorModel) : State<Nothing>()
    object Loading : State<Nothing>()

    companion object {
        fun <T> successes(data: T) = Successes(data)
        fun error(errorModel: ErrorModel) = Error(errorModel)
        fun loading() = Loading
    }
}