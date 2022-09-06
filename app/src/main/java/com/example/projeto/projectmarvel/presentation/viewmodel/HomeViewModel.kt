package com.example.projeto.projectmarvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projeto.projectmarvel.base.BaseViewModel
import com.example.projeto.projectmarvel.domain.Results
import com.example.projeto.projectmarvel.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {


    private val _charactersState = MutableLiveData<Results.MarvelApiResults>()
    val characterState: LiveData<Results.MarvelApiResults> = _charactersState

    var page: Int = 1
    var pageSize: Int = 10
    var isLastPage: Boolean = false
    var isLoading = MutableLiveData<Boolean>().apply { value = false }

    private var apiKey: String = "3de0b497819406575965404fa8b3ab5f"
    private var hash: String = "6e1ffd75c5aef7b135f1ab706ddb53c4"
    private var ts: String = "20220902172850"
    private var limit: Int = 100


    fun setSearch() {
        executeGetCharacters(apiKey, hash, ts, limit)
    }

    fun executeGetCharacters(
        apikey: String,
        hash: String,
        ts: String,
        limit: Int
    ) {
        viewModelScope.launch {
            homeUseCase.execute(apikey, hash, ts, limit)
                .flowOn(Dispatchers.Main)
                .onStart { _charactersState.value = Results.MarvelApiResults.Loading }
                .catch { _charactersState.value = Results.MarvelApiResults.Failure(it) }
                .collect { _charactersState.value = Results.MarvelApiResults.Success(it) }
        }

    }

}