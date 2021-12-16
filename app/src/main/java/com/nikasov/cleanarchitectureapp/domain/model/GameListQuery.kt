package com.nikasov.cleanarchitectureapp.domain.model

data class GameListQuery(
    val search: String? = null,
    val developers: String? = null,
    val genres: String? = null,
    val tags: String? = null,
    val dates: String? = null,
    val ordering: String? = null
) {



}