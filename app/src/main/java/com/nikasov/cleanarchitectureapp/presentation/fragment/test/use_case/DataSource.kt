package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class DataSource @Inject constructor() {

    suspend fun getTitle(): DataState<String> {
        delay(2000)
//        return DataState.error(ErrorModel.getLocalError("Title error"))
        return DataState.successes("Title")
    }

    suspend fun getDescription(): DataState<String> {
        delay(4000)
//        return DataState.error(ErrorModel.getLocalError("Description error"))
        return DataState.successes("Description")
    }

    suspend fun getAmount(): DataState<Int> {
        delay(1000)
//        return DataState.error(ErrorModel.getLocalError("Amount error"))
        return DataState.successes(69)
    }
}