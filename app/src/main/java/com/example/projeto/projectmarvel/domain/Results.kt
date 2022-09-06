package com.example.projeto.projectmarvel.domain

import com.example.projeto.projectmarvel.data.model.BaseModel

class Results {

    sealed class MarvelApiResults {
        object Loading : MarvelApiResults()
        data class Success(val characterList : BaseModel) : MarvelApiResults()
        data class Failure(val throwable: Throwable) : MarvelApiResults()
    }
}