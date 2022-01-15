package com.nikasov.cleanarchitectureapp.domain.model

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



}
