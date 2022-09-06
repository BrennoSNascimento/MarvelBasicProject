package com.example.projeto.projectmarvel.domain.repository

import com.example.projeto.projectmarvel.data.homeApi.HomeApi
import com.example.projeto.projectmarvel.data.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepository {
    override suspend fun getCharacters(
        apikey: String,
        hash: String,
        ts : String,
        limit : Int
    ): Flow<BaseModel> = flow {
        emit(homeApi.getCharacters(apikey,hash,ts,limit))
    }

}