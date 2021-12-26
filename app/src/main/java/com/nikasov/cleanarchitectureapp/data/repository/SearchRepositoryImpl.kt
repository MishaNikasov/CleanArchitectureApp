package com.nikasov.cleanarchitectureapp.data.repository

import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem
import com.nikasov.cleanarchitectureapp.domain.repository.SearchRepository
import com.nikasov.cleanarchitectureapp.presentation.base.BaseRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : BaseRepository(), SearchRepository {
    override suspend fun searchGenres(): DataState<List<GameDetailsInfoItem.Genre>> {
        return obtainResponse(
            request = networkApi.getGenres(),
            mapper = {
                it?.results?.map { item -> item.toGenre() }
            }
        )
    }

    override suspend fun searchDevelopers(): DataState<List<GameDetailsInfoItem.Developer>> {
        return obtainResponse(
            request = networkApi.getDevelopers(),
            mapper = {
                it?.results?.map { item -> item.toDeveloper() }
            }
        )
    }

    override suspend fun searchTags(): DataState<List<GameDetailsInfoItem.Tag>> {
        return obtainResponse(
            request = networkApi.getTags(),
            mapper = {
                it?.results?.map { item -> item.toTag() }
            }
        )
    }

//    override suspend fun searchStores(): DataState<List<GameDetailsInfoItem.Store>> {
//        return obtainResponse(
//            request = networkApi.getGenres(genreId),
//            mapper = {
//                it?.map { item -> item.toGenre() }
//            }
//        )
//    }
}