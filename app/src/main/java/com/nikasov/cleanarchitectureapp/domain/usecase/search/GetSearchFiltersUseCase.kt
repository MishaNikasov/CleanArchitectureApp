package com.nikasov.cleanarchitectureapp.domain.usecase.search

import com.nikasov.cleanarchitectureapp.common.utils.State
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.domain.repository.SearchRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchFiltersUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(): Flow<State<MutableList<GameDetailsInfo>>> = flow {
        emit(State.loading())
        val filterList: MutableList<GameDetailsInfo> = arrayListOf()

        coroutineScope {
            listOf(
                async {
                    searchRepository.searchGenres().getResult { list ->
                        filterList.add(GameDetailsInfo.Genres(list ?: arrayListOf()))
                    }
                },
                async {
                    searchRepository.searchDevelopers().getResult { list ->
                        filterList.add(GameDetailsInfo.Developers(list ?: arrayListOf()))
                    }
                },
                async {
                    searchRepository.searchTags().getResult { list ->
                        filterList.add(GameDetailsInfo.Tags(list ?: arrayListOf()))
                    }
                }
            ).awaitAll()
        }

        emit(State.successes(filterList))
    }

}