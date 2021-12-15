package com.nikasov.cleanarchitectureapp.presentation.fragment.screenshot

import androidx.viewpager2.widget.ViewPager2
import com.nikasov.cleanarchitectureapp.databinding.FragmentScreenshotsBinding
import com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots.FullscreenScreenshotAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScreenshotsFragment : BaseFragment<FragmentScreenshotsBinding>(FragmentScreenshotsBinding::inflate) {

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
                        setCounterText(position)
                    }
                })
            }
        }
    }

    private fun setCounterText(currentItem: Int) {
        val counterText = "${currentItem + 1}/${screenshots.size}"
        requireBinding().counter.text = counterText
    }

}