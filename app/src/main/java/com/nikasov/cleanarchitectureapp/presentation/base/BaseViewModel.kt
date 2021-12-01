//package com.nmible.patients.app.util.base
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.nmible.patients.app.data.entity.error.ErrorModel
//import com.nmible.patients.app.util.DataState
//import com.nmible.patients.app.util.UiState
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.launch
//import timber.log.Timber
//import kotlin.coroutines.CoroutineContext
//
//open class BaseViewModel : ViewModel() {
//    protected val _uiState =  MutableLiveData<UiState>()
//    val uiState: LiveData<UiState> = _uiState
//
//    protected val exceptionHandler = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
//        _uiState.value = UiState.Loading(false)
//        _uiState.value = UiState.Error(ErrorModel.getLocalError(throwable.localizedMessage ?: "Error"))
//        Timber.e(throwable.localizedMessage)
//    }
//
//    fun <Model> handleRequest(
//        request: (suspend () -> DataState<Model>),
//        successAction: ((Model?) -> Unit)? = null,
//        errorAction: ((ErrorModel) -> Unit)? = null,
//        isLoading: Boolean = true,
//    ) {
//        viewModelScope.launch(exceptionHandler) {
//            if (isLoading)
//                _uiState.value = UiState.Loading(true)
//            when (val state = request()) {
//                is DataState.Success -> {
//                    if (isLoading)
//                        _uiState.value = UiState.Loading(false)
//                    if (successAction != null) {
//                        successAction(state.data)
//                    } else {
//                        _uiState.value = UiState.Successes(state.data)
//                    }
//                }
//                is DataState.Error -> {
//                    if (isLoading)
//                        _uiState.value = UiState.Loading(false)
//                    if (errorAction != null) {
//                        errorAction(state.errorModel)
//                    } else {
//                        _uiState.value = UiState.Error(state.errorModel)
//                    }
//                }
//            }
//        }
//    }
//}