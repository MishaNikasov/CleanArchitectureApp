package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.Game

data class GameDto(
    val added: Int,
    val added_by_status: AddedByStatusDto,
    val background_image: String,
    val esrb_rating: EsrbRatingDto,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val platforms: List<PlatformDto>,
    val playtime: Int,
    val rating: Int,
    val rating_top: Int,
    val ratings: RatingsDto,
    val ratings_count: Int,
    val released: String,
    val reviews_text_count: String,
    val slug: String,
    val suggestions_count: Int,
    val tba: Boolean,
    val updated: String
) {
    fun toGame() = Game(
        added = added,
        backgroundImage = background_image,
        id = id,
        metacritic = metacritic,
        name = name,
        platforms = platforms.map { it.toPlatform() },
        playtime = playtime,
        rating = rating,
        released = released,
    )
}