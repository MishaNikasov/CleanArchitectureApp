package com.nikasov.cleanarchitectureapp.domain.model

sealed class GameDetailsInfo(
    open val content: List<GameDetailsInfoItem>
) {

    data class Developers(override val content: List<GameDetailsInfoItem>): GameDetailsInfo(content)
    data class Genres(override val content: List<GameDetailsInfoItem>) : GameDetailsInfo(content)
    data class Tags(override val content: List<GameDetailsInfoItem>) : GameDetailsInfo(content)

    val header: String
    get() {
        return when (this) {
            is Developers -> "Developers"
            is Genres -> "Genres"
            is Tags -> "Tags"
        }
    }
}