package com.nikasov.cleanarchitectureapp.presentation.fragment.test.shared_state

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import com.nikasov.cleanarchitectureapp.databinding.FragmentTestBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedStateFragment: BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private val viewModel: SharedStateViewModel by viewModels()

    override fun setupViews() {
        requireBinding().apply {
            loader.isVisible = false

            button.setOnClickListener {
                viewModel.putValue()
            }

            button2.setOnClickListener {
                requireBinding().textContainer.text = ""
                requireBinding().textContainer2.text = ""

                lifecycleScope.launch {
                    viewModel.completedValue.collect {
                        requireContext().showToast(it.toString())
                    }
                }
            }
        }
    }

    override fun setupViewModelCallbacks() {
        viewModel.apply {
            counter.collectWhenStarted(this@SharedStateFragment) {
                requireBinding().textContainer.text = it.toString()
            }
            completedValue.collectWhenStarted(this@SharedStateFragment) {
                requireBinding().textContainer2.text = it.toString()
            }
        }
    }
}