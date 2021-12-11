package com.nikasov.cleanarchitectureapp.domain.model

data class Game(
    val added: Int,
    val backgroundImage: String,
    val id: String,
    val metacritic: Float,
    val name: String,
    val platforms: List<Platform>,
    val playtime: Float,
    val rating: Float,
    val released: String,
)