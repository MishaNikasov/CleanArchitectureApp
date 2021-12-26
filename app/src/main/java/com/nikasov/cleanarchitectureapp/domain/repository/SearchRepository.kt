package com.nikasov.cleanarchitectureapp.domain.repository

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem

interface SearchRepository {
    suspend fun searchGenres(): DataState<List<GameDetailsInfoItem.Genre>>
    suspend fun searchDevelopers(): DataState<List<GameDetailsInfoItem.Developer>>
    suspend fun searchTags(): DataState<List<GameDetailsInfoItem.Tag>>
//    suspend fun searchStores(): DataState<List<GameDetailsInfoItem.Store>>
}