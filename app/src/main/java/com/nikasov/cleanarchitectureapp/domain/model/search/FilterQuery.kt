package com.nikasov.cleanarchitectureapp.domain.model.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class FilterQuery(open val query: String?): Parcelable {

    @Parcelize
    data class Search(
        override val query: String
    ): FilterQuery(query)

    @Parcelize
    data class Developers(
        override val query: String
    ): FilterQuery(query)

    @Parcelize
    data class Genres(
        override val query: String
    ): FilterQuery(query)

    @Parcelize
    data class Tags(
        override val query: String
    ): FilterQuery(query)

    @Parcelize
    data class OrderingType(
        override val query: String
    ): FilterQuery(query)

    @Parcelize
    object Empty: FilterQuery(null)

}

inline fun <reified T: FilterQuery> returnQuery(list: List<FilterQuery>): String? {
    val queryStr = list.filterIsInstance<T>().joinToString { it.query ?: "" }
    return if (queryStr.isBlank())
        null
    else
        queryStr
}