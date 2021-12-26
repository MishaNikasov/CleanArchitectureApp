package com.nikasov.cleanarchitectureapp.common.utils

sealed class DataState<out T> {

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

    suspend fun getResult(block: suspend (T?) -> Unit) {
        if (this  is Success) {
            block(data)
        }
    }

    fun getResult(
        successesBlock: (T?) -> Unit,
        errorBlock: ((ErrorModel) -> Unit)? = null
    ) {
        when (this) {
            is Success -> successesBlock(data)
            is Error -> errorBlock?.invoke(errorModel)
        }
    }
}