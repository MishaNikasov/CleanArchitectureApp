package com.nikasov.cleanarchitectureapp.domain.model.search

import java.io.Serializable

enum class OrderingType(val type: String?): Serializable {
    NAME("name"),
    RELEASED("released"),
    ADDED("added"),
    CREATED("created"),
    UPDATED("updated"),
    RATING("rating"),
    METACRITIC("metacritic"),
    EMPTY(null)
}