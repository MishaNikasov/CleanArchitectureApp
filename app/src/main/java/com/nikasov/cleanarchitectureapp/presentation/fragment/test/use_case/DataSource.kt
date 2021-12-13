package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import kotlinx.coroutines.delay
import javax.inject.Inject

interface DataSource {
    suspend fun getTitle(): DataState<String>
    suspend fun getDescription(): DataState<String>
    suspend fun getAmount(): DataState<Int>
}