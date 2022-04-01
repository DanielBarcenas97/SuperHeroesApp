package com.dan.superheroesapp.domain

import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.data.remote.HeroesService

class HeroRepository {

    private val api = HeroesService()

    suspend fun getHeroById(id: Int): ApiHeroModel? {
        return api.getHeroesById(id)
    }
}