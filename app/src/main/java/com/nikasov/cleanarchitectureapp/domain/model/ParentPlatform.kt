package com.nikasov.cleanarchitectureapp.domain.model

import androidx.annotation.DrawableRes
import com.nikasov.cleanarchitectureapp.R

data class ParentPlatform(
    val id: Int,
    val name: String,
    val slug: String
) {

    companion object {
        private const val PC = "pc"
        private const val XBOX = "xbox"
        private const val PLAYSTATION = "playstation"
        private const val MAC = "mac"
    }

    @DrawableRes
    val icon = when (slug) {
        PC -> R.drawable.ic_platform_windows
        XBOX -> R.drawable.ic_platform_xbox
        PLAYSTATION -> R.drawable.ic_platform_playstation
        MAC -> R.drawable.ic_platform_mac
        else -> R.drawable.ic_platform_windows
    }
}
