package com.nikasov.cleanarchitectureapp.domain.model

import java.time.LocalDate

data class GameDetails(
    var name: String,
    var description: String,
    var metacriticScore: Int,
    var releaseDate: LocalDate?,
    var coverImage: String,
    var developers: List<GameDetailsInfoItem.Developer>?,
    var website: String,
    var tags: List<GameDetailsInfoItem.Tag>?,
    var genres: List<GameDetailsInfoItem.Genre>?,
    var playtime: Int,
    var achievementsCount: Int,
    var platforms: List<ParentPlatform>,
)