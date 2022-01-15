package com.nikasov.cleanarchitectureapp.presentation.fragment.hub_screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentHubScreenBinding
import com.nikasov.cleanarchitectureapp.presentation.adapter.game.GameListAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import com.nikasov.cleanarchitectureapp.presentation.routers.MainRouter
import com.nikasov.cleanarchitectureapp.presentation.util.NavigationBarSettings
import com.nikasov.cleanarchitectureapp.presentation.util.ScreenSettings
import com.nikasov.cleanarchitectureapp.presentation.util.StatusBarSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubScreenFragment: BaseFragment<FragmentHubScreenBinding>(FragmentHubScreenBinding::inflate) {

    private val hubScreenViewModel: HubScreenViewModel by viewModels()

    @Inject
    lateinit var gameListAdapter: GameListAdapter

    override fun setupViews() {
        val router = MainRouter(findNavController())
        requireBinding().gameRecycler.apply {
            gameListAdapter.onGameClick = {
                router.openGameDetailsScreen(it.id)
            }
            adapter = gameListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupViewModelCallbacks() {
        hubScreenViewModel.gameList.collectWhenStarted(this) { gameList ->
            loadingState(false)
            gameListAdapter.submitData(gameList)
        }
    }

    override fun loadingState(isLoading: Boolean) {
        requireBinding().loader.isVisible = isLoading
    }

    override fun getScreenSettings(): ScreenSettings {
        return ScreenSettings(
            statusBarSettings = StatusBarSettings(R.color.link_black, true),
            navigationBarSettings = NavigationBarSettings(R.color.black, lightBottomBar = false, navigationBarIsPresent = true)
        )
    }
}