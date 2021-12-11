package com.nikasov.cleanarchitectureapp.data.remote.dto.game_list

import com.nikasov.cleanarchitectureapp.domain.model.Game

data class GameDto(
    val added: Int? = null,
    val background_image: String? = null,
    val esrb_rating: EsrbRatingDto? = null,
    val id: String? = null,
    val metacritic: Float? = null,
    val name: String? = null,
    val platforms: List<PlatformDto>? = null,
    val playtime: Float? = null,
    val rating: Float? = null,
    val rating_top: Int? = null,
    val ratings_count: Int? = null,
    val released: String? = null,
    val reviews_text_count: String? = null,
    val slug: String? = null,
    val suggestions_count: Int? = null,
    val tba: Boolean? = null,
    val updated: String? = null
) {
    fun toGame() = Game(
        added = added ?: -1,
        backgroundImage = background_image ?: "",
        id = id ?: "",
        metacritic = metacritic ?: -1f,
        name = name ?: "",
        playtime = playtime ?: -1f,
        rating = rating ?: -1f,
        released = released ?: "",
    )
}