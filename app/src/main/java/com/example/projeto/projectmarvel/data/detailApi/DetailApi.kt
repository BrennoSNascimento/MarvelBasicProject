package com.example.projeto.projectmarvel.data.detailApi

import com.example.projeto.projectmarvel.data.detailEndPoint.DetailEndPoint
import com.example.projeto.projectmarvel.data.model.ComicsResult
import javax.inject.Inject

class DetailApi @Inject constructor(private val detailEndPoint: DetailEndPoint) {
    suspend fun getComics(characterId : String, apikey : String, hash: String,ts : String) : ComicsResult =
        detailEndPoint.getComics(characterId,apikey,hash,ts)
}