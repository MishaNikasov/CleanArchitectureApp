package com.nikasov.cleanarchitectureapp.common

sealed class State<out T> {

    data class Successes<out T>(val data: T) : State<T>()
    data class Error<out T>(val errorModel: ErrorModel, val data: T? = null) : State<T>()
    data class Loading<out T>(val data: T? = null) : State<T>()

    companion object {
        fun <T> successes(data: T) = Successes(data)
        fun <T> error(errorModel: ErrorModel, data: T? = null) = Error(errorModel, data)
        fun <T> loading(data: T? = null) = Loading(data)
    }
}