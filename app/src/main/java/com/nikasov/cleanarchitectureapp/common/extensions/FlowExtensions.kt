package com.nikasov.cleanarchitectureapp.common.extensions

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun LifecycleOwner.addRepeatingJob(
    state: Lifecycle.State,
    controlContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycleScope.launch(controlContext) {
        lifecycle.repeatOnLifecycle(state, block)
    }
}

inline fun <T> Flow<T>.collectWhenStarted(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit
) {
    lifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        collect(action)
    }
}

suspend fun <T> MutableStateFlow<MutableList<T>>.addItem(item: T) {
    val list = arrayListOf<T>()
    list.addAll(this.value)
    list.add(item)
    emit(list)
}

suspend fun <T> MutableStateFlow<MutableList<T>>.removeItem(index: Int) {
    val list = arrayListOf<T>()
    list.addAll(this.value)
    list.removeAt(index)
    emit(list)
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

