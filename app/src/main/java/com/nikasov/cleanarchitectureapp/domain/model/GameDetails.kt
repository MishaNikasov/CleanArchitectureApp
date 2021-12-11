package com.nikasov.cleanarchitectureapp.domain.model

import java.time.LocalDate

data class GameDetails(
    var name: String,
    var description: String,
    var metacriticScore: Int,
    var releaseDate: LocalDate,
    var coverImage: String,
    var website: String,
    var playtime: Int,
    var achievementsCount: Int,
    var platforms: List<ParentPlatform>,

)