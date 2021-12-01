package com.nikasov.cleanarchitectureapp.presentation.base

import com.nikasov.cleanarchitectureapp.common.DataState
import com.nikasov.cleanarchitectureapp.common.ErrorModel
import com.nikasov.cleanarchitectureapp.common.getErrorModel
import retrofit2.Response

open class BaseRepository {
    fun <Model> obtainResponse(
        response: Response<Model?>
    ): DataState<Model> {
        return try {
            if (response.isSuccessful) {
                DataState.successes(response.body())
            } else {
                DataState.error(response.getErrorModel())
            }
        } catch (e: Exception) {
            DataState.error(ErrorModel.getLocalError(e.localizedMessage ?: "Something went wrong"))
        }
    }
}