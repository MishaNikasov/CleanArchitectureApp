package com.nikasov.cleanarchitectureapp.presentation.fragment.favorite_screenshots

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.common.extensions.getScreenWidth
import com.nikasov.cleanarchitectureapp.databinding.FragmentFavoriteScreenshotsBinding
import com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots.GameScreenshotsAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import com.nikasov.cleanarchitectureapp.presentation.routers.MainRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteScreenshotsFragment: BaseFragment<FragmentFavoriteScreenshotsBinding>(FragmentFavoriteScreenshotsBinding::inflate) {

    private val favoriteScreenshotsViewModel: FavoriteScreenshotsViewModel by viewModels()

    @Inject
    lateinit var gameScreenshotsAdapter: GameScreenshotsAdapter

    private val router by lazy { MainRouter(findNavController()) }

    companion object {
        private const val SPAN_COUNT = 3
    }

    override fun setupViews() {
        with(requireBinding()) {
            with(screenshotsRecycler) {
                adapter = gameScreenshotsAdapter
                layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            }
            gameScreenshotsAdapter.cellSize = getCellSize()
            gameScreenshotsAdapter.onScreenshotClick = { _, position ->
                router.openScreenshotsFragment(position, favoriteScreenshotsViewModel.screenshotList.value)
            }
        }
    }

    override fun setupViewModelCallbacks() {
        favoriteScreenshotsViewModel.screenshotList.collectWhenStarted(this) {
            gameScreenshotsAdapter.submitList(it)
        }
    }

    private fun getCellSize() = requireActivity().getScreenWidth()/SPAN_COUNT

}