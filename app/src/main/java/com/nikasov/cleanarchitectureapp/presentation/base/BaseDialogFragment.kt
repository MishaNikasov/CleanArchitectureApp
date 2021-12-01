package com.nikasov.cleanarchitectureapp.presentation.base

import android.os.Bundle
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.nikasov.cleanarchitectureapp.common.extensions.hideKeyboard
import timber.log.Timber

open class BaseDialogFragment<FragmentBinding : ViewBinding>(
    @LayoutRes layoutId: Int
) : DialogFragment(layoutId) {

    lateinit var binding: FragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideKeyboard()
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

    override fun onResume() {
        super.onResume()
    }

    open fun errorState(errorMsg: String = "") {
        hideKeyboard()
        loadingState(false)
        Timber.e("Error: $errorMsg")
    }

    open fun disableError() {}

    open fun loadingState(isLoading: Boolean) {
        hideKeyboard()
    }

    open fun refresh() {}

}