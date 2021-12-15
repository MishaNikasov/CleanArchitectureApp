package com.nikasov.cleanarchitectureapp.presentation.fragment.favorite_screenshots

import androidx.lifecycle.viewModelScope
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.domain.usecase.screenshots.GetAllFavoritesScreenshotsUseCase
import com.nikasov.cleanarchitectureapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenshotsViewModel @Inject constructor(
    getAllFavoritesScreenshotsUseCase: GetAllFavoritesScreenshotsUseCase
): BaseViewModel() {

    val screenshotList = getAllFavoritesScreenshotsUseCase().stateIn(viewModelScope, SharingStarted.Lazily, arrayListOf())

}