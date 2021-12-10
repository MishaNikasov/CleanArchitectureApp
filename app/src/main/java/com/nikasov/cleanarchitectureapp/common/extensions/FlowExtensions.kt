package com.nikasov.cleanarchitectureapp.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun LifecycleOwner.addRepeatingJob(
    state: Lifecycle.State,
    controlContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) = lifecycleScope.launch(controlContext) {
    lifecycle.repeatOnLifecycle(state, block)
}

inline fun <T> Flow<T>.collectWhenStarted(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit
) {
    lifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        collect(action)
    }
}

/**
 *
 * Without extensions will look like ->
 *
 *  lifecycleScope.launch {
 *      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
 *          viewModel.stateFlow
 *              //.flowWithLifecycle(lifecycle)
 *              // to avoid repeatOnLifecycle
 *              .collect { }
 *      }
 *  }
**/

