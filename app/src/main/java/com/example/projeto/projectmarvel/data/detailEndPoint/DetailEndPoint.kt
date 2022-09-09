package com.example.projeto.projectmarvel.data.detailEndPoint

import com.example.projeto.projectmarvel.data.model.ComicsResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailEndPoint {
    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId : String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
        //@Query("limit") limit: Int,
    ): ComicsResult
}