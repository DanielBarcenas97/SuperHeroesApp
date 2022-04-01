package com.dan.superheroesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dan.superheroesapp.R
import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val heroViewModel: HeroViewModel by viewModels()

    companion object {
        var currentHero: ApiHeroModel? = null
        var scrolling = 100
        var firstscrolling = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroViewModel.getHeroes()
    }
}