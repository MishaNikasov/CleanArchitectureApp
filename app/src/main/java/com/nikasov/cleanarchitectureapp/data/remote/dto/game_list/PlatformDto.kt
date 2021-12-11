package com.nikasov.cleanarchitectureapp.data.remote.dto.game_list

data class PlatformDto(
    val platform: PlatformDetailDto? = null,
    val released_at: String? = null,
    val requirements: RequirementsDto? = null
)