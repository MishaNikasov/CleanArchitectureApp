package com.nikasov.cleanarchitectureapp.domain.model

data class Game(
    val added: Int,
    val backgroundImage: String,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val platforms: List<Platform>,
    val playtime: Int,
    val rating: Int,
    val released: String,
)