package com.example.projeto.projectmarvel.domain.repository

import com.example.projeto.projectmarvel.data.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getCharacters(
        apikey: String,
        hash: String,
        ts: String,
        limit: Int
    ): Flow<BaseModel>
}