package com.nikasov.cleanarchitectureapp.presentation.base

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import com.nikasov.cleanarchitectureapp.presentation.util.NavigationBarSettings
import com.nikasov.cleanarchitectureapp.presentation.util.ScreenSettings
import com.nikasov.cleanarchitectureapp.presentation.util.StatusBarSettings

abstract class BaseActivity<ActivityBinding : ViewBinding> (
    private val binder: (LayoutInflater) -> ActivityBinding
) : AppCompatActivity() {

    private var binding: ActivityBinding? = null

    protected fun requireBinding(): ActivityBinding {
        return checkNotNull(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = binder(layoutInflater)
        this.binding = binding
        setContentView(binding.root)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    abstract fun setScreenSettings(screenSettings: ScreenSettings)

    protected fun setupStatusBar(actionBarSettings: StatusBarSettings) {
        window.statusBarColor = this.getColor(actionBarSettings.color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ViewCompat.getWindowInsetsController(window.decorView)?.apply {
                isAppearanceLightStatusBars = !actionBarSettings.lightStatusBar
            }
        } else {
            var flags = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility = when (actionBarSettings.lightStatusBar) {
                true -> View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv().let { flags = flags and it; flags }
                else -> View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.let { flags = flags or it; flags }
            }
        }
    }

    protected fun setupBottomBar(navigationBarSettings: NavigationBarSettings) {
        val decorView: View = window.decorView
        var flags = decorView.systemUiVisibility
        flags = if (!navigationBarSettings.lightBottomBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        decorView.systemUiVisibility = flags
        window.navigationBarColor = getColor(navigationBarSettings.color)
    }

}