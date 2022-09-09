package com.example.projeto.projectmarvel.domain.usecase

import com.example.projeto.projectmarvel.data.model.ComicsResult
import com.example.projeto.projectmarvel.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val detailRepository: DetailRepository) {
    suspend fun execute(
        characterId: String,
        apikey: String,
        hash: String,
        ts: String
    ): Flow<ComicsResult> {
        return detailRepository.getComics(characterId,apikey, hash, ts)
    }


}