package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.common.extensions.htmlText
import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.databinding.FragmentGameDetailsBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class GameDetailsFragment: BaseFragment<FragmentGameDetailsBinding>(FragmentGameDetailsBinding::inflate) {

    @InternalCoroutinesApi
    private val gameDetailsViewModel: GameDetailsViewModel by viewModels()

    override fun getData() {
        gameDetailsViewModel.getGameDetail()
    }

    override fun setupViewModelCallbacks() {
        gameDetailsViewModel.gameDetailState.collectWhenStarted(this) { state ->
            state.getResult(
                loading = { loadingState(true) },
                successes = {
                    setupGameDetails(it?.gameDetails)
                    loadingState(false)
                },
                error = {
                    errorState(it)
                    loadingState(false)
                }
            )
        }
    }

    private fun setupGameDetails(gameDetails: GameDetails?) {
        gameDetails ?: return
        requireBinding().apply {
            backgroundCover.load(gameDetails.coverImage)
            name.text = gameDetails.name
            description.htmlText(gameDetails.description)
        }
    }

    override fun loadingState(isLoading: Boolean) {
        requireBinding().loader.isVisible = isLoading
    }

}