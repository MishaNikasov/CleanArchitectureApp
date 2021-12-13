package com.nikasov.cleanarchitectureapp.domain.model

sealed class GameDetailsInfo(
    open val content: String
) {

    data class Developers(override val content: String): GameDetailsInfo(content)
    data class Genres(override val content: String) : GameDetailsInfo(content)

    val header: String
    get() {
        return when (this) {
            is Developers -> "Developers"
            is Genres -> "Genres"
        }
    }
}