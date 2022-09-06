package com.example.projeto.projectmarvel.domain.usecase

import com.example.projeto.projectmarvel.data.model.BaseModel
import com.example.projeto.projectmarvel.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun execute(
        apikey: String,
        hash: String,
        ts: String,
        limit: Int
    ): Flow<BaseModel> {
        return homeRepository.getCharacters(apikey,hash,ts,limit)
    }


}