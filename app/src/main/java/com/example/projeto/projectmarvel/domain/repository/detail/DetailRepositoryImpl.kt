package com.example.projeto.projectmarvel.domain.repository.detail

import com.example.projeto.projectmarvel.data.detailApi.DetailApi
import com.example.projeto.projectmarvel.data.model.ComicsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailApi: DetailApi) : DetailRepository {
    override suspend fun getComics(
        characterId : String,
        apikey: String,
        hash: String,
        ts : String

    ): Flow<ComicsResult> = flow {
        emit(detailApi.getComics( characterId,apikey,hash,ts))
    }

}