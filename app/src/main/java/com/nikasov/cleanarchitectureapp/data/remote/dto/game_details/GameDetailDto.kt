package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

import com.nikasov.cleanarchitectureapp.domain.model.GameDetails
import java.time.LocalDate

data class GameDetailDto(
    val achievements_count: Int? = null,
    val added: Int? = null,
    val added_by_status: AddedByStatus? = null,
    val additions_count: Int? = null,
    val alternative_names: List<String>? = null,
    val background_image: String? = null,
    val background_image_additional: String? = null,
    val clip: Any? = null,
    val creators_count: Int? = null,
    val description: String? = null,
    val description_raw: String? = null,
    val developers: List<Developer>? = null,
    val dominant_color: String? = null,
    val esrb_rating: EsrbRating? = null,
    val game_series_count: Int? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val metacritic: Int? = null,
    val metacritic_platforms: List<MetacriticPlatform>? = null,
    val metacritic_url: String? = null,
    val movies_count: Int? = null,
    val name: String? = null,
    val name_original: String? = null,
    val parent_achievements_count: Int? = null,
    val parent_platforms: List<ParentPlatformDto>? = null,
    val parents_count: Int? = null,
    val platforms: List<PlatformXX>? = null,
    val playtime: Int? = null,
    val publishers: List<Publisher>? = null,
    val rating: Double? = null,
    val rating_top: Int? = null,
    val ratings: List<Rating>? = null,
    val ratings_count: Int? = null,
    val reactions: Reactions? = null,
    val reddit_count: Int? = null,
    val reddit_description: String? = null,
    val reddit_logo: String? = null,
    val reddit_name: String? = null,
    val reddit_url: String? = null,
    val released: String? = null,
    val reviews_count: Int? = null,
    val reviews_text_count: Int? = null,
    val saturated_color: String? = null,
    val screenshots_count: Int? = null,
    val slug: String? = null,
    val stores: List<Store>? = null,
    val suggestions_count: Int? = null,
    val tags: List<Tag>? = null,
    val tba: Boolean? = null,
    val twitch_count: Int? = null,
    val updated: String? = null,
    val user_game: Any? = null,
    val website: String? = null,
    val youtube_count: Int? = null
) {
    fun toGameDetail(): GameDetails {
        return GameDetails(
            name = name ?: "",
            description = description ?: "",
            metacriticScore = metacritic ?: -1,
            //released
            releaseDate = LocalDate.now(),
            coverImage = background_image ?: "",
            website = website ?: "",
            playtime = playtime ?: -1,
            achievementsCount = achievements_count ?: -1,
            platforms = parent_platforms?.map { it.platform.toParentPlatform() } ?: arrayListOf()
        )
    }
}