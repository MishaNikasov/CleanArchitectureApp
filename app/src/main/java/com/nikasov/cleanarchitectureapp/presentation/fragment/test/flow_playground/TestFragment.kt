package com.nikasov.cleanarchitectureapp.presentation.fragment.test.flow_playground

import androidx.fragment.app.viewModels
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentTestBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TestFragment: BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private val testViewModel: SecondTestViewModel by viewModels()

    override fun getData() {
        testViewModel.getData()
        testViewModel.zipScreenState.collectWhenStarted(this) {
            Timber.d(it.toString())
        }
    }

    override fun setupViewModelCallbacks() {
//        testViewModel.screenState.collectWhenStarted(this) { state ->
//            when (state) {
//                is State.Loading -> loadingState(true)
//                is State.Error -> {
//                    loadingState(false)
//                    Timber.d("Error: ${state.data}")
//                }
//                is State.Successes -> {
//                    loadingState(false)
//                    Timber.d("Successes: ${state.data}")
//                }
//            }
//        }
    }

    override fun loadingState(isLoading: Boolean) {
        Timber.d("Loading is -> $isLoading")
    }

}