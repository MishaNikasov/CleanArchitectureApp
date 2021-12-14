package com.nikasov.cleanarchitectureapp.data.remote.util.game

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import retrofit2.HttpException

private const val PAGE_SIZE = 10

class GameScreenshotsPageSource (
    private val id: String,
    private val networkApi: NetworkApi
): PagingSource<Int, GameScreenshot>() {

    override fun getRefreshKey(state: PagingState<Int, GameScreenshot>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameScreenshot> {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize

        val response = networkApi.getGameScreenshots(id, pageNumber, pageSize)
        return if (response.isSuccessful) {
            val gameList = response.body()?.results?.map { it.toGameScreenshot() } ?: arrayListOf()
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