package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikasov.cleanarchitectureapp.common.State
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentGameListBinding
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.presentation.adapter.game.GameListAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameListFragment: BaseFragment<FragmentGameListBinding>(FragmentGameListBinding::inflate) {

    private val gameListViewModel: GameListViewModel by viewModels()

    @Inject
    lateinit var gameListAdapter: GameListAdapter

    override fun setData() {
        gameListViewModel.getGameList()
    }

    override fun setupViews() {
        requireBinding().gameRecycler.apply {
            adapter = gameListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupViewModelCallbacks() {
        gameListViewModel.state.collectWhenStarted(this) {state ->
            when (state) {
                is State.Loading -> {
                    loadingState(true)
                }
                is State.Error -> {
                    loadingState(false)
                    errorState(state.errorModel)
                }
                is State.Successes -> {
                    state.data ?: return@collectWhenStarted
                    loadingState(false)
                    setupGameList(state.data.gameList)
                }
            }
        }
    }

    private fun setupGameList(gameList: List<Game>) {
        gameListAdapter.submitList(gameList)
    }
}