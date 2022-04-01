package com.dan.superheroesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.domain.GetHeroes
import com.dan.superheroesapp.ui.MainActivity.Companion.firstscrolling
import com.dan.superheroesapp.ui.MainActivity.Companion.scrolling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroViewModel : ViewModel() {

    val heroModel = MutableLiveData<MutableList<ApiHeroModel>>()
    var list : MutableList<ApiHeroModel> = mutableListOf()

    var getHeroes = GetHeroes()


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