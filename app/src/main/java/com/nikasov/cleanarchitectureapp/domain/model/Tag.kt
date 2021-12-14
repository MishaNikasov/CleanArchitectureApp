package com.nikasov.cleanarchitectureapp.domain.model

data class Tag(
    val id: Int,
    val gamesCount: Int,
    val name: String,
    val slug: String
)
