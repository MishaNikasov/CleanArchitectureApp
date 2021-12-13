package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.common.utils.getCommonErrorModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class GetTestScreenUseCase @Inject constructor(
    private val dataSource: DataSource
) {

    operator fun invoke(
        additionalString: String
    ): Flow<State<TestScreenState>> = flow {

        Timber.d("Flow started")

        val totalTime = measureTimeMillis {
            emit(State.loading())

            var title: String? = null
            var description: String? = null
            var amount: Int? = null

            val errorList = arrayListOf<ErrorModel>()

            coroutineScope {
                listOf(
                    async {
                        dataSource.getTitle().getResult(
                            successesBlock = { title = it },
                            errorBlock = { errorList.add(it) }
                        )
                    },
                    async {
                        dataSource.getDescription().getResult(
                            successesBlock = { description = it },
                            errorBlock = { errorList.add(it) }
                        )
                    },
                    async {
                        dataSource.getAmount().getResult(
                            successesBlock = { amount = it },
                            errorBlock = { errorList.add(it) }
                        )
                    }
                ).awaitAll()
            }

            if (errorList.isEmpty()) {
                emit(State.successes(
                    TestScreenState(
                        "$title + additionalString: $additionalString",
                        description,
                        amount
                    )
                ))
            } else {
                emit(State.error(errorList.getCommonErrorModel()))
            }
        }

        Timber.d("Flow finished in ${TimeUnit.MILLISECONDS.toSeconds(totalTime)} seconds")
    }

}