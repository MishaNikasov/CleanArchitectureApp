package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

data class Publisher(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)