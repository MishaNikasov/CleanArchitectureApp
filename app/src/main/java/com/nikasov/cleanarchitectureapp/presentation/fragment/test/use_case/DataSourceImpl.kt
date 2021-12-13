package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import kotlinx.coroutines.delay

class DataSourceImpl(): DataSource {
    override suspend fun getTitle(): DataState<String> {
        delay(2000)
//        return DataState.error(ErrorModel.getLocalError("Title error"))
        return DataState.successes("Title")
    }

    override suspend fun getDescription(): DataState<String> {
        delay(4000)
//        return DataState.error(ErrorModel.getLocalError("Description error"))
        return DataState.successes("Description")
    }

    override suspend fun getAmount(): DataState<Int> {
        delay(1000)
//        return DataState.error(ErrorModel.getLocalError("Amount error"))
        return DataState.successes(69)
    }
}