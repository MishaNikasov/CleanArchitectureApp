package com.nikasov.cleanarchitectureapp.presentation.fragment.favorite_screenshots

import androidx.fragment.app.viewModels
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentFavoriteScreenshotsBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteScreenshotsFragment: BaseFragment<FragmentFavoriteScreenshotsBinding>(FragmentFavoriteScreenshotsBinding::inflate) {

    private val favoriteScreenshotsViewModel: FavoriteScreenshotsViewModel by viewModels()

    override fun setupViewModelCallbacks() {
        favoriteScreenshotsViewModel.screenshotList.collectWhenStarted(this) {

        }
    }

}