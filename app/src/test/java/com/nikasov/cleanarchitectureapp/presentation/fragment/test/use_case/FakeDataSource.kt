package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.nikasov.cleanarchitectureapp.common.utils.DataState

class FakeDataSource: DataSource {

    override suspend fun getTitle(): DataState<String> {
//        return DataState.error(ErrorModel.getLocalError("Title error"))
        return DataState.successes("Title")
    }

    override suspend fun getDescription(): DataState<String> {
//        return DataState.error(ErrorModel.getLocalError("Description error"))
        return DataState.successes("Description")
    }

    override suspend fun getAmount(): DataState<Int> {
//        return DataState.error(ErrorModel.getLocalError("Amount error"))
        return DataState.successes(69)
    }

}