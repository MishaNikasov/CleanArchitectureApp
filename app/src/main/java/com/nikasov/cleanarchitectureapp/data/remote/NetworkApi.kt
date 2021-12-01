package com.nikasov.cleanarchitectureapp.data.remote

import com.nikasov.cleanarchitectureapp.data.remote.dto.GameListDto
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET(NetworkUrl.GAMES)
    fun getGamesList(

    ): Response<GameListDto?>
}