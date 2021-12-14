package com.nikasov.cleanarchitectureapp.domain.model

import androidx.annotation.DrawableRes
import com.nikasov.cleanarchitectureapp.R

sealed class GameDetailsInfoItem(
    open val id: Int,
    open val name: String,
    open val slug: String
) {

    data class Publisher(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class Genre(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class Store(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class Developer(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class EsrbRating(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class Tag(
        override val id: Int,
        val gamesCount: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug)

    data class ParentPlatform(
        override val id: Int,
        override val name: String,
        override val slug: String
    ): GameDetailsInfoItem(id, name, slug) {

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

}
