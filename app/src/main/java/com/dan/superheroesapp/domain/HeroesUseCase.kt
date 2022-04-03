package com.dan.superheroesapp.domain

import com.dan.superheroesapp.data.model.ApiHeroModel

class HeroesUseCase(
    private val repository:HeroRepository = HeroRepository()
) {

    suspend  fun getHeroById(id: Int): ApiHeroModel? {
        return repository.getHeroById(id)
    }
}