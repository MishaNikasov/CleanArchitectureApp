package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import com.nikasov.cleanarchitectureapp.common.utils.ErrorModel
import com.nikasov.cleanarchitectureapp.databinding.FragmentTestBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TestUseCaseFragment: BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private val viewModel: ViewModelWithUseCase by viewModels()

    override fun setupViewModelCallbacks() {
        viewModel.screenState.collectWhenStarted(this) { state ->
            state.getResult(
                loading = { loadingState(true) },
                successes = {
                    loadingState(false)
                    showText(it.toString())
                    requireContext().showToast("Screen updated}", Toast.LENGTH_LONG)
                },
                error = { error ->
                    loadingState(false)
                    errorState(error)
                }
            )
        }

//        viewModel.screenStateLiveData.observe(viewLifecycleOwner) { state ->
//            state.getResult(
//                loading = { loadingState(true) },
//                successes = {
//                    loadingState(false)
//                    showText(it.toString())
//                    requireContext().showToast("Screen updated}", Toast.LENGTH_LONG)
//                },
//                error = { error ->
//                    loadingState(false)
//                    errorState(error)
//                }
//            )
//
//        }
    }

    private fun showText(text: String) {
        requireBinding().textContainer.text = text
    }

    override fun loadingState(isLoading: Boolean) {
        requireBinding().loader.isVisible = isLoading
    }

    override fun errorState(errorModel: ErrorModel) {
        showText(errorModel.getErrorMessage())
    }

}