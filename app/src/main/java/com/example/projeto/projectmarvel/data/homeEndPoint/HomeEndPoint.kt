package com.example.projeto.projectmarvel.data.homeEndPoint

import com.example.projeto.projectmarvel.data.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeEndPoint {
    @GET("characters")
    suspend fun getCHaracters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int,
    ): BaseModel
}