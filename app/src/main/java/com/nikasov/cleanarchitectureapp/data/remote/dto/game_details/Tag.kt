package com.nikasov.cleanarchitectureapp.data.remote.dto.game_details

data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val language: String,
    val name: String,
    val slug: String
)