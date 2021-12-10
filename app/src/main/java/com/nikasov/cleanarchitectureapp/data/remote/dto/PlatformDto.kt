package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.Platform

data class PlatformDto(
    val platform: PlatformDetailDto? = null,
    val released_at: String? = null,
    val requirements: RequirementsDto? = null
) {
    fun toPlatform() = Platform(
        detail = platform?.toPlatformDetail(),
        releasedAt = released_at
    )
}