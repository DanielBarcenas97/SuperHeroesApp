package com.dan.superheroesapp.domain

import com.dan.superheroesapp.data.model.ApiHeroModel

class GetHeroes {

    private val repository = HeroRepository()

    suspend  fun getHeroById(id: Int): ApiHeroModel? {
        return repository.getHeroById(id)
    }
}