package com.nikasov.cleanarchitectureapp.presentation.fragment.game_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nikasov.cleanarchitectureapp.common.State
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import com.nikasov.cleanarchitectureapp.databinding.FragmentGameListBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GameListFragment: BaseFragment<FragmentGameListBinding>(FragmentGameListBinding::inflate) {

    private val gameListViewModel: GameListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelCallbacks()
    }

    private fun setupViewModelCallbacks() {
        lifecycleScope.launchWhenCreated {
            gameListViewModel.state.collect { state ->
                when (state) {
                    is State.Loading -> {
                        loadingState(true)
                    }
                    is State.Error -> {
                        loadingState(false)
                        errorState(state.errorModel)
                    }
                    is State.Successes -> {
                        loadingState(false)
                        requireContext().showToast("${ state.data?.gameList?.size ?: 0 }")
                    }
                }
            }
        }
    }

}