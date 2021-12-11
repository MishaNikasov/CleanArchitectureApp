package com.nikasov.cleanarchitectureapp.presentation.fragment.game_details

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
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
            when (state) {
                is State.Loading -> loadingState(true)
                is State.Error -> {
                    loadingState(false)
                }
                is State.Successes -> {
                    setupGameDetails(state.data.gameDetails)
                    loadingState(false)
                }
            }
        }
    }

    private fun setupGameDetails(gameDetails: GameDetails?) {
        gameDetails ?: return
        requireBinding().apply {
            name.text = gameDetails.name
        }
    }

    override fun loadingState(isLoading: Boolean) {
        requireBinding().loader.isVisible = isLoading
    }

}