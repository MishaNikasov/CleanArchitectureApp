package com.nikasov.cleanarchitectureapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.nikasov.cleanarchitectureapp.common.ErrorModel
import com.nikasov.cleanarchitectureapp.common.extensions.hideKeyboard
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import timber.log.Timber

open class BaseFragment<FragmentBinding : ViewBinding>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBinding
) : Fragment() {

    private var binding: FragmentBinding? = null

    protected fun requireBinding(): FragmentBinding {
        return checkNotNull(binding)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = binder(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideKeyboard()
    }

    private fun handleLostConnection() {
        Timber.d("handleLostConnection ${this.binding}")
    }

    fun handleBackPress(lifecycleOwner: LifecycleOwner, action: () -> Unit) {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                lifecycleOwner
            ) {
                action()
            }
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    open fun errorState(errorModel: ErrorModel) {
        hideKeyboard()
        loadingState(false)
        Timber.e("Error: ${errorModel.getErrorMessage()}")
        requireContext().showToast(errorModel.getErrorMessage())
    }

    open fun loadingState(isLoading: Boolean) {}

    open fun refresh() {}

    open fun backPressAction() {}

}