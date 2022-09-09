package com.example.projeto.projectmarvel.domain.repository.detail

import com.example.projeto.projectmarvel.data.model.ComicsResult
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getComics(
        apikey: String,
        hash: String,
        ts: String,
        characterId: String
    ): Flow<ComicsResult>
}