package com.nikasov.cleanarchitectureapp.presentation.fragment.screenshot

import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.usecase.screenshots.ToggleFavoriteUseCase
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenshotViewModel @Inject constructor(
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): BaseViewModel() {

    fun toggleFavorite(gameScreenshot: GameScreenshot) {
        viewModelScope.launch { toggleFavoriteUseCase(gameScreenshot) }
    }

}