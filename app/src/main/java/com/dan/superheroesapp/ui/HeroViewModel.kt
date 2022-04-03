package com.dan.superheroesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.domain.HeroesUseCase
import com.dan.superheroesapp.ui.MainActivity.Companion.firstscrolling
import com.dan.superheroesapp.ui.MainActivity.Companion.scrolling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroViewModel : ViewModel() {


    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    val heroModel = MutableLiveData<MutableList<ApiHeroModel>>()
    var list : MutableList<ApiHeroModel> = mutableListOf()

    var getHeroes = HeroesUseCase()


    init {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }


    fun getHeroes(){
        for (i in firstscrolling..scrolling) {
            viewModelScope.launch {
                val result = getHeroById(i)
                result?.let {
                    updateLiveData(it)
                } ?: kotlin.run {
                    val retry = getHeroById(i)
                    retry?.let {
                        updateLiveData(it)
                    }
                }
            }
        }

    }

    private suspend fun getHeroById(i: Int) = getHeroes.getHeroById(i)


    private suspend fun updateLiveData(hero: ApiHeroModel) {
        list.add(hero)
        list.sortBy {
            it.id.toInt()
        }
        withContext(Dispatchers.Main.immediate) {
            heroModel.value = list
        }
    }

}