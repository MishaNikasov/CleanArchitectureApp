package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import coil.load
import com.nikasov.cleanarchitectureapp.common.extensions.DEFAULT_DATE
import com.nikasov.cleanarchitectureapp.common.extensions.byPattern
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.common.extensions.htmlText
import com.nikasov.cleanarchitectureapp.databinding.FragmentGameDetailsBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.HorizontalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.VerticalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.FilterType
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.GameDetailsInfoAdapter
import com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots.GameScreenshotsAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import com.nikasov.cleanarchitectureapp.presentation.routers.MainRouter
import com.nikasov.cleanarchitectureapp.presentation.util.addPlatforms
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@AndroidEntryPoint
class GameDetailsFragment: BaseFragment<FragmentGameDetailsBinding>(FragmentGameDetailsBinding::inflate) {

    @InternalCoroutinesApi
    private val gameDetailsViewModel: GameDetailsViewModel by viewModels()

    @Inject
    lateinit var gameDetailsInfoAdapter: GameDetailsInfoAdapter

    @Inject
    lateinit var gameScreenshotsAdapter: GameScreenshotsAdapter

    private val router by lazy { MainRouter(findNavController()) }

    override fun getData() {
        gameDetailsViewModel.getGameDetail()
    }

    override fun setupViews() {
        requireBinding().apply {
            with(infoRecycler) {
                adapter = gameDetailsInfoAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(VerticalSpaceDecoration())

                gameDetailsInfoAdapter.filterType = FilterType.Action { query ->
                    router.openGameListFragment(listOf(query))
                }
            }
            with(screenshotsRecycler) {
                adapter = gameScreenshotsAdapter
                layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = HORIZONTAL }
                addItemDecoration(HorizontalSpaceDecoration(6))
            }
        }
    }

    override fun setupViewModelCallbacks() {
        gameDetailsViewModel.gameDetailState.collectWhenStarted(this) { state ->
            state.getResult(
                loading = { loadingState(true) },
                successes = { screenState ->
                    screenState ?: return@getResult
                    setupGameDetails(screenState.gameDetails)
                    setupInfoList(screenState.infoList)
                    setupScreenshots(screenState.screenshots)
                    loadingState(false)
                },
                error = {
                    errorState(it)
                    loadingState(false)
                }
            )
        }
    }

    private fun setupScreenshots(screenshots: List<GameScreenshot>) {
        gameScreenshotsAdapter.submitList(screenshots)
        gameScreenshotsAdapter.onScreenshotClick = { _, position ->
            router.openScreenshotsFragment(position, screenshots)
        }
    }

    private fun setupInfoList(infoList: List<GameDetailsInfo>?) {
        gameDetailsInfoAdapter.submitList(infoList)
    }

    private fun setupGameDetails(gameDetails: GameDetails?) {
        requireBinding().apply {
            gameDetails ?: return
            backgroundCover.load(gameDetails.coverImage)
            name.text = gameDetails.name
            description.htmlText(gameDetails.description)
            releaseDate.text = gameDetails.releaseDate.byPattern(DEFAULT_DATE)
            platformContainer.addPlatforms(requireContext(), gameDetails.platforms)
        }
    }

    override fun loadingState(isLoading: Boolean) {
        requireBinding().loader.isVisible = isLoading
    }

}