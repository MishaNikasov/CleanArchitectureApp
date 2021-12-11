package com.nikasov.cleanarchitectureapp.presentation.util

import androidx.annotation.ColorRes
import com.nikasov.cleanarchitectureapp.R

//TODO: finish this

data class ScreenSettings(
    val actionBarSettings: ActionBarSettings,
    val navigationBarSettings: NavigationBarSettings// = NavigationBarSettings()
)

data class NavigationBarSettings(
    @ColorRes val color: Int = R.color.white,
    val lightBottomBar: Boolean = false,
    val navigationBarIsPresent: Boolean = true
)

data class ActionBarSettings(
    @ColorRes val color: Int,
    val lightStatusBar: Boolean = true
)