package com.nikasov.cleanarchitectureapp.presentation.activity

import androidx.core.view.isVisible
import com.nikasov.cleanarchitectureapp.databinding.ActivityMainBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseActivity
import com.nikasov.cleanarchitectureapp.presentation.util.ScreenSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setScreenSettings(screenSettings: ScreenSettings) {
        setupStatusBar(screenSettings.statusBarSettings)
        setupBottomBar(screenSettings.navigationBarSettings)
        requireBinding().bottomNavigation.isVisible = screenSettings.navigationBarSettings.navigationBarIsPresent
    }

}