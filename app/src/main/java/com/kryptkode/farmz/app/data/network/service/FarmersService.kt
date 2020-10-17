package com.kryptkode.farmz.app.data.network.service

import com.kryptkode.farmz.app.data.network.response.FarmersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FarmersService {

    @GET("get-sample-farmers")
    suspend fun getFarmers(
        @Query("limit") limit: Int,
    ): FarmersResponse
}

