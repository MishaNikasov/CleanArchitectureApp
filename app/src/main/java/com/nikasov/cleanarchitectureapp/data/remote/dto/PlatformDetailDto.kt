package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.PlatformDetail

data class PlatformDetailDto(
    val id: Int,
    val name: String,
    val slug: String
) {
    fun toPlatformDetail() = PlatformDetail(
        id = id,
        name = name,
        slug = slug
    )
}