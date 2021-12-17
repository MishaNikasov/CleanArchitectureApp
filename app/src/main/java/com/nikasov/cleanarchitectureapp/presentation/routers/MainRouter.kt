package com.nikasov.cleanarchitectureapp.presentation.routers

import androidx.navigation.NavController
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.presentation.fragment.game_details.GameDetailsFragmentArgs
import com.nikasov.cleanarchitectureapp.presentation.fragment.game_list.GameListFragmentArgs
import com.nikasov.cleanarchitectureapp.presentation.fragment.screenshot.ScreenshotsFragmentArgs

class MainRouter(
    var navController: NavController
) {

    fun openGameDetailsScreen(gameId: String) {
        val args = GameDetailsFragmentArgs.Builder(gameId).build().toBundle()
        navController.navigate(R.id.gameDetailsFragment, args)
    }

    fun openScreenshotsFragment(selectedScreenshotPosition: Int, screenshots: List<GameScreenshot>) {
        val args = ScreenshotsFragmentArgs.Builder(selectedScreenshotPosition, screenshots.toTypedArray()).build().toBundle()
        navController.navigate(R.id.screenshotsFragment, args)
    }

    fun openGameListFragment(filterQuery: List<FilterQuery>?) {
        val args = GameListFragmentArgs.Builder(filterQuery?.toTypedArray()).build().toBundle()
        navController.navigate(R.id.gameListFragment, args)
    }

}