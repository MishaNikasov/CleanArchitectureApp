package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
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
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.HorizontalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.VerticalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.game.GameScreenshotsAdapter
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.GameDetailsInfoAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import com.nikasov.cleanarchitectureapp.presentation.util.addPlatforms
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import timber.log.Timber
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

    override fun getData() {
        gameDetailsViewModel.getGameDetail()
    }

    override fun setupViews() {
        requireBinding().apply {
            with(infoRecycler) {
                adapter = gameDetailsInfoAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(VerticalSpaceDecoration())
            }
            with(screenshotsRecycler) {
                adapter = gameScreenshotsAdapter
                layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = HORIZONTAL }
                addItemDecoration(HorizontalSpaceDecoration())
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
                    loadingState(false)
                },
                error = {
                    errorState(it)
                    loadingState(false)
                }
            )
        }
        gameDetailsViewModel.screenshotsList.collectWhenStarted(this) { list ->
            Timber.d("Screenshots $list")
            gameScreenshotsAdapter.submitData(list)
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