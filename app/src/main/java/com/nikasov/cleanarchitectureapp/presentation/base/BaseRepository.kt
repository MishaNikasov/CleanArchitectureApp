package com.nikasov.cleanarchitectureapp.presentation.base

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.common.utils.Mapper
import com.nikasov.cleanarchitectureapp.common.utils.getErrorModel
import retrofit2.Response

open class BaseRepository {

    fun <DTO, Model> obtainResponse(
        block: Response<DTO?>,
        mapper: Mapper<DTO?, Model?>
    ): DataState<Model> {
        return try {
            val mappedResponseBody = mapper(block.body())
            if (block.isSuccessful) {
                DataState.successes(mappedResponseBody)
            } else {
                DataState.error(block.getErrorModel())
            }
        } catch (e: Exception) {
            DataState.error(ErrorModel.getLocalError(e.localizedMessage ?: "Something went wrong"))
        }
    }

}