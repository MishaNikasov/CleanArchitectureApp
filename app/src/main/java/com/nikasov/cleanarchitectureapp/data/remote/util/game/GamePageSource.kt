package com.nikasov.cleanarchitectureapp.data.remote.util.game

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.domain.model.search.returnQuery
import retrofit2.HttpException

private const val PAGE_SIZE = 10

class GamePageSource(
    private val networkApi: NetworkApi,
    private val gameListQuery: List<FilterQuery>
) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize

        val response = networkApi.getGamesList(
            page = pageNumber,
            pageSize = pageSize,
            search = gameListQuery.firstOrNull { it is FilterQuery.Search }?.query,
            developers = returnQuery<FilterQuery.Developers>(gameListQuery),
            genres = returnQuery<FilterQuery.Genres>(gameListQuery),
            tags = returnQuery<FilterQuery.Tags>(gameListQuery),
            ordering = gameListQuery.firstOrNull { it is FilterQuery.OrderingType }?.query
        )

        return if (response.isSuccessful) {
            val gameList = response.body()?.results?.map { it.toGame() } ?: arrayListOf()
            val nextKey = if (gameList.size < pageSize) null else calculateNextPage(pageSize, pageNumber)
            val prevKey = if (pageNumber == 1) null else pageNumber - 1
            LoadResult.Page(gameList, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    private fun calculateNextPage(pageSize: Int, currentPage: Int) = (pageSize / PAGE_SIZE) + currentPage

    val pager = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
            enablePlaceholders = false
        ),
        pagingSourceFactory = { this }
    )
}