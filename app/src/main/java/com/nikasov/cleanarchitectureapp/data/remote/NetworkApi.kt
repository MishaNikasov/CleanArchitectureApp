package com.nikasov.cleanarchitectureapp.data.remote

import com.nikasov.cleanarchitectureapp.data.remote.dto.game_details.GameDetailDto
import com.nikasov.cleanarchitectureapp.data.remote.dto.game_list.GameListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET(NetworkUrl.GAMES)
    suspend fun getGamesList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<GameListDto?>

    @GET(NetworkUrl.GAME_DETAIL)
    suspend fun getGameDetails(
        @Path ("id") id: String
    ): Response<GameDetailDto?>
}