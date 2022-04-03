package com.dan.superheroesapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dan.superheroesapp.R
import com.dan.superheroesapp.databinding.FragmentDetailBinding
import com.dan.superheroesapp.ui.MainActivity.Companion.currentHero

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHero()
    }

    private fun initHero() {
        setData()
        setBio()
        setAppearance()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        Glide.with(this)
            .load(currentHero!!.image.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.ivPhoto)

        binding.tvName.text = currentHero?.name

        binding.tvPower.text = getString(R.string.power)+ getString(R.string.blank_space) + currentHero?.powerstats?.power
        binding.tvDurability.text = getString(R.string.durability) + getString(R.string.blank_space)+ currentHero?.powerstats?.durability
        binding.tvSpeed.text = getString(R.string.velocity)+ getString(R.string.blank_space) + currentHero?.powerstats?.speed
        binding.tvIntelligence.text = getString(R.string.intelligence)+ getString(R.string.blank_space) + currentHero?.powerstats?.intelligence
        binding.tvCombat.text = getString(R.string.combat) + getString(R.string.blank_space)+ currentHero?.powerstats?.combat
        binding.tvStrength.text = getString(R.string.strength)+ getString(R.string.blank_space) + currentHero?.powerstats?.strength

    }

    private fun setBio() {
        binding.tvNameComplete.text = currentHero?.biography?.fullName
        binding.tvAlias.text = currentHero?.biography?.alias.toString()
        binding.tvDate.text = currentHero?.biography?.firstAppearance
        binding.tvCompany.text = currentHero?.biography?.publisher
        binding.tvBand.text = currentHero?.biography?.alignment
    }

    private fun setAppearance() {
        binding.tvGender.text = currentHero?.appearance?.gender
        binding.tvWeight .text = currentHero?.appearance?.weight.toString()
        binding.tvHeight.text = currentHero?.appearance?.height.toString()
    }

}