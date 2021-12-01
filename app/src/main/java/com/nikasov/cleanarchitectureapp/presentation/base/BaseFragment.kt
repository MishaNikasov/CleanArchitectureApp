package com.nikasov.cleanarchitectureapp.presentation.base

import android.os.Bundle
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
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    lateinit var binding: FragmentBinding

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