package com.nikasov.cleanarchitectureapp.domain.model

import androidx.annotation.DrawableRes

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
        PC -> 1
        XBOX -> 1
        PLAYSTATION -> 1
        MAC -> 1
        else -> 2
    }
}
