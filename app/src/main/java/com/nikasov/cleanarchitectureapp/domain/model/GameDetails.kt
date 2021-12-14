package com.nikasov.cleanarchitectureapp.domain.model

import java.time.LocalDate

data class GameDetails(
    var name: String,
    var description: String,
    var metacriticScore: Int,
    var releaseDate: LocalDate?,
    var coverImage: String,
    var developers: List<Developer>?,
    var website: String,
    var tags: List<Tag>?,
    var genres: List<Genre>?,
    var playtime: Int,
    var achievementsCount: Int,
    var platforms: List<ParentPlatform>,
)