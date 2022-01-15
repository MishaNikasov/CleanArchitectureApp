package com.nikasov.cleanarchitectureapp.presentation.fragment.screenshot

import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.nikasov.cleanarchitectureapp.databinding.FragmentScreenshotsBinding
import com.nikasov.cleanarchitectureapp.domain.model.FavoriteState
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots.FullscreenScreenshotAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import com.nikasov.cleanarchitectureapp.presentation.util.DialogHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScreenshotsFragment : BaseFragment<FragmentScreenshotsBinding>(FragmentScreenshotsBinding::inflate) {

    private val screenshotViewModel: ScreenshotViewModel by viewModels()

    private val selectedScreenshotPosition by lazy { ScreenshotsFragmentArgs.fromBundle(requireArguments()).selectedScreenshotPosition }
    private val screenshots by lazy { ScreenshotsFragmentArgs.fromBundle(requireArguments()).screenshots }

    @Inject
    lateinit var fullscreenScreenshotAdapter: FullscreenScreenshotAdapter

    override fun setData() {
        fullscreenScreenshotAdapter.submitList(screenshots.toList())
        requireBinding().screenshotViewPager.setCurrentItem(selectedScreenshotPosition, false)
        setCounterText(selectedScreenshotPosition)
    }

    override fun setupViews() {
        with(requireBinding()) {
            with(screenshotViewPager) {
                adapter = fullscreenScreenshotAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        setupFavoriteState(screenshots[position])
                        setCounterText(position)
                    }
                })
            }

            favoriteBtn.setOnClickListener {
                val currentItem = screenshots[screenshotViewPager.currentItem]
                screenshotViewModel.toggleFavorite(currentItem)
                DialogHelper(requireContext()).deleteFromFavorite()
                toggleFavoriteState(currentItem)
            }
        }
    }

    private fun setCounterText(currentItem: Int) {
        val counterText = "${currentItem + 1}/${screenshots.size}"
        requireBinding().counter.text = counterText
    }

    private fun setupFavoriteState(currentItem: GameScreenshot) {
        when (currentItem.isFavorite) {
            true -> requireBinding().favoriteBtn.load(FavoriteState.FAVORITE.icon)
            false -> requireBinding().favoriteBtn.load(FavoriteState.DEFAULT.icon)
        }
    }

    private fun toggleFavoriteState(currentItem: GameScreenshot) {
        currentItem.isFavorite = !currentItem.isFavorite
        setupFavoriteState(currentItem)
    }
}