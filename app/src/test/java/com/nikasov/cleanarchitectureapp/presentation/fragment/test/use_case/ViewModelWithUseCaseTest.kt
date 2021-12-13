package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import com.google.common.truth.Truth.assertThat
import com.nikasov.cleanarchitectureapp.common.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ViewModelWithUseCaseTest {

    private lateinit var viewModelWithUseCase: ViewModelWithUseCase
    private lateinit var dataSource: DataSource
    private lateinit var getTestScreenUseCase: GetTestScreenUseCase

    @Before
    fun init() {
        dataSource = FakeDataSource()
        getTestScreenUseCase = GetTestScreenUseCase(dataSource)
        viewModelWithUseCase = ViewModelWithUseCase(getTestScreenUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getResult() = runTest(StandardTestDispatcher()) {
        viewModelWithUseCase.screenState.collect { state ->
            state.getResult(
                loading = {
                    assertThat(state).isEqualTo(State.Loading::class.java)
                },
                successes = { screenState ->
                    assertThat(screenState).isEqualTo(TestScreenState::class.java)
                    assertThat(screenState?.amount).isEqualTo(69)
                },
                error = { error ->
                    assertThat(state).isEqualTo(State.Error::class.java)
                    assertThat(error.getErrorMessage()).contains("")
                }
            )
        }
    }

}