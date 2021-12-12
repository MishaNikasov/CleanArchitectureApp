package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import androidx.fragment.app.viewModels
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentTestBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TestUseCaseFragment: BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private val viewModel: ViewModelWithUseCase by viewModels()

    override fun setupViewModelCallbacks() {
        viewModel.screenState.collectWhenStarted(this) {
            Timber.d(it.toString())
        }
    }

}