package com.example.projeto.projectmarvel.data.homeApi

import com.example.projeto.projectmarvel.data.homeEndPoint.HomeEndPoint
import com.example.projeto.projectmarvel.data.model.BaseModel
import javax.inject.Inject

class HomeApi @Inject constructor(private val homeEndPoint: HomeEndPoint) {
    suspend fun getCharacters( apikey : String, hash: String,ts : String, limit : Int) : BaseModel =
        homeEndPoint.getCHaracters(apikey,hash,ts,limit)
}