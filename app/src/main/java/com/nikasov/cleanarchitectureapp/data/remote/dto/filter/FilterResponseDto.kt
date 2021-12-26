package com.nikasov.cleanarchitectureapp.data.remote.dto.filter

data class FilterResponseDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<FilterDto>
)