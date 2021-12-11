package com.nikasov.cleanarchitectureapp.presentation.routers

import androidx.navigation.NavController
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.presentation.fragment.game_details.GameDetailsFragmentArgs

class MainRouter(
    var navController: NavController
) {

    fun openGameDetailsScreen(gameId: String) {
        val args = GameDetailsFragmentArgs.Builder(gameId).build().toBundle()
        navController.navigate(R.id.gameDetailsFragment, args)
    }

}