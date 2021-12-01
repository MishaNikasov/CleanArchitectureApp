package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.Platform

data class PlatformDto(
    val platform: PlatformDetailDto,
    val released_at: String,
    val requirements: RequirementsDto
) {
    fun toPlatform() = Platform(
        detail = platform.toPlatformDetail(),
        releasedAt = released_at
    )
}