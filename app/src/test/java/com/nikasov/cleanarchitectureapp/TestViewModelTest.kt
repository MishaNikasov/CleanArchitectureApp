package com.nikasov.cleanarchitectureapp

import com.nikasov.cleanarchitectureapp.presentation.fragment.test.TestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TestViewModelTest {

    lateinit var testViewModel: TestViewModel
    @Before
    fun init() {
        testViewModel = TestViewModel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `run parallel request`() = runTest {
        testViewModel.getData()

        testViewModel.screenState.collect {
            print(it.toString())
        }
    }

}