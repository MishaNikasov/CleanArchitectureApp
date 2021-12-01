package com.nikasov.cleanarchitectureapp.common

import com.google.gson.Gson
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

data class ErrorModel(
    var errorCode: String = "",
    var errorMessage: String = ""
) {

    companion object {
        fun getLocalError(msg: String) = ErrorModel(errorMessage = msg)
    }

    @JvmName("funGetErrorMessage")
    fun getErrorMessage(): String = errorMessage
}


fun createErrorModelResponseBody(msg: String): ResponseBody {
    return Gson().toJson(ErrorModel.getLocalError(msg)).toResponseBody()
}

/**
 * For retrofit response
 */
fun Response<*>?.getErrorModel(): ErrorModel {
    try {
        this?.errorBody()?.let { response ->
            val errorModel = Gson().fromJson(response.string(), ErrorModel::class.java)
            return errorModel ?: ErrorModel()
        }
        return ErrorModel()
    } catch (e: Exception) {
        return ErrorModel()
    }
}

/**
 * For okhttp response
 */
fun okhttp3.Response?.getErrorModel(): ErrorModel {
    try {
        this?.peekBody(2048)?.let { response ->
            val errorModel = Gson().fromJson(response.string(), ErrorModel::class.java)
            return errorModel ?: ErrorModel()
        }
        return ErrorModel()
    } catch (e: Exception) {
        return ErrorModel()
    }
}
