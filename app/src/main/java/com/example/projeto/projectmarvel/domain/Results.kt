package com.example.projeto.projectmarvel.domain

import com.example.projeto.projectmarvel.data.model.BaseModel
import com.example.projeto.projectmarvel.data.model.ComicsResult

class Results {

    sealed class MarvelApiResults {
        object Loading : MarvelApiResults()
        data class Success(val characterList : BaseModel) : MarvelApiResults()
        data class Failure(val throwable: Throwable) : MarvelApiResults()
    }

    sealed class MarvelApiComicsResults {
        object Loading : MarvelApiComicsResults()
        data class Success(val comicsList : ComicsResult) : MarvelApiComicsResults()
        data class Failure(val throwable: Throwable) : MarvelApiComicsResults()
    }
}