package com.nikasov.cleanarchitectureapp.domain.usecase.game

import javax.inject.Inject

data class GameUseCases @Inject constructor(
    val getGamesListUseCase: GetGamesListUseCase
)