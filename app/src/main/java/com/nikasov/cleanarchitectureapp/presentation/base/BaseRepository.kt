package com.nikasov.cleanarchitectureapp.presentation.base

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.common.utils.getErrorModel
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

//    override suspend fun getGamesList(): DataState<GameList?> {
//        return when (val response = obtainResponse(networkApi.getGamesList())) {
//            is DataState.Success -> DataState.successes(response.data?.toGameList())
//            is DataState.Error -> DataState.error(response.errorModel)
//        }
//    }