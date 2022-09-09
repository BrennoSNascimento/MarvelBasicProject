package com.example.projeto.projectmarvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projeto.projectmarvel.base.BaseViewModel
import com.example.projeto.projectmarvel.domain.Results
import com.example.projeto.projectmarvel.domain.usecase.DetailUseCase
import com.example.projeto.projectmarvel.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject internal constructor(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    private val _comicsState = MutableLiveData<Results.MarvelApiComicsResults>()
    val characterState: LiveData<Results.MarvelApiComicsResults> = _comicsState

    var page: Int = 1
    var pageSize: Int = 10
    var isLastPage: Boolean = false
    var isLoading = MutableLiveData<Boolean>().apply { value = false }

    private var apiKey: String = "3de0b497819406575965404fa8b3ab5f"
    private var hash: String = "6e1ffd75c5aef7b135f1ab706ddb53c4"
    private var ts: String = "20220902172850"
    //private var limit: Int = 20


    fun setSearch(characterId : String) {
        executeGetComics(characterId,apiKey, hash, ts)
    }

    fun executeGetComics(
        characterId: String,
        apikey: String,
        hash: String,
        ts: String
    ) {
        viewModelScope.launch {
            detailUseCase.execute(characterId,apikey, hash, ts)
                .flowOn(Dispatchers.Main)
                .onStart { _comicsState.value = Results.MarvelApiComicsResults.Loading }
                .catch { _comicsState.value = Results.MarvelApiComicsResults.Failure(it) }
                .collect { _comicsState.value = Results.MarvelApiComicsResults.Success(it) }
        }

    }

}