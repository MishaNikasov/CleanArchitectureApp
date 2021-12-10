package com.nikasov.cleanarchitectureapp.data.remote.util.game

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikasov.cleanarchitectureapp.common.utils.DataState
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.usecase.game.GetGamesListUseCase
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import javax.inject.Inject

class GamePageSource (
    private val networkApi: NetworkApi
): PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize

        val response = networkApi.getGamesList(pageNumber, pageSize)
        return if (response.isSuccessful) {
            val gameList = response.body()?.results?.map { it.toGame() } ?: arrayListOf()
            val nextKey = if (gameList.size < pageSize) null else pageNumber + 1
            val prevKey = if (pageNumber == 1) null else pageNumber - 1
            LoadResult.Page(gameList, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}