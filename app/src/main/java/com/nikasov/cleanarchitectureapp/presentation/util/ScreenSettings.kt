package com.nikasov.cleanarchitectureapp.presentation.util

import androidx.annotation.ColorRes
import com.nikasov.cleanarchitectureapp.R

data class ScreenSettings(
    val statusBarSettings: StatusBarSettings,
    val navigationBarSettings: NavigationBarSettings
) {
    companion object {
        fun default() = ScreenSettings(
            statusBarSettings = StatusBarSettings(),
            navigationBarSettings = NavigationBarSettings()
        )
    }
}

data class NavigationBarSettings(
    @ColorRes val color: Int = R.color.black,
    val lightBottomBar: Boolean = false,
    val navigationBarIsPresent: Boolean = false
)

data class StatusBarSettings(
    @ColorRes val color: Int = R.color.black,
    val lightStatusBar: Boolean = true
)