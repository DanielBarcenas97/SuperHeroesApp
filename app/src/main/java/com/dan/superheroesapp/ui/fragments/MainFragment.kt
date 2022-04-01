package com.dan.superheroesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dan.superheroesapp.R
import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.databinding.FragmentMainBinding
import com.dan.superheroesapp.ui.HeroViewModel
import com.dan.superheroesapp.ui.MainActivity.Companion.currentHero
import com.dan.superheroesapp.ui.MainActivity.Companion.firstscrolling
import com.dan.superheroesapp.ui.MainActivity.Companion.scrolling
import com.dan.superheroesapp.ui.adapter.SuperHeroAdapter


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: SuperHeroAdapter
    private val heroViewModel: HeroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerView()
        heroViewModel.heroModel.observe(viewLifecycleOwner) {
            adapter.setHeroList(it)
        }

        checkIsBottomScroll()

        return binding.root
    }

    private fun checkIsBottomScroll() {
        binding.mainRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    updateIndex()
                }
            }
        })
    }

    private fun updateIndex() {
        firstscrolling = scrolling + 1
        scrolling += 20
        heroViewModel.getHeroes()
    }

    private fun initRecyclerView() {
        binding.mainRV.layoutManager = LinearLayoutManager(activity)
        adapter = SuperHeroAdapter() { hero ->
            onClickItem(
                hero
            )
        }
        binding.mainRV.adapter = adapter
    }

    private fun onClickItem(heroModel: ApiHeroModel) {
        currentHero = heroModel
        Navigation.findNavController(binding.mainRV)
            .navigate(R.id.action_mainFragment_to_detailFragment)
    }


}