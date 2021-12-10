package com.nikasov.cleanarchitectureapp.data.remote.dto

import com.nikasov.cleanarchitectureapp.domain.model.PlatformDetail

data class PlatformDetailDto(
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null
) {
    fun toPlatformDetail() = PlatformDetail(
        id = id ?: -1,
        name = name ?: "",
        slug = slug ?: ""
    )
}