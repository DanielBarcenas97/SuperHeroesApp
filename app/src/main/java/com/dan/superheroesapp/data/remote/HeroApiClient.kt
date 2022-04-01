package com.dan.superheroesapp.data.remote

import com.dan.superheroesapp.data.model.ApiHeroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface HeroApiClient {

    @GET("{id}")
    suspend fun getHeroesById(@Path("id") id: Int): Response<ApiHeroModel>
}