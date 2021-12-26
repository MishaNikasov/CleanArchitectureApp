package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery

sealed class FilterType {

    object Selection : FilterType()

    data class Action(
        val action: (FilterQuery) -> Unit
    ) : FilterType()

}