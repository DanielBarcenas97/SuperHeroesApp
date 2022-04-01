package com.dan.superheroesapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dan.superheroesapp.data.model.ApiHeroModel
import com.dan.superheroesapp.databinding.ItemBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBinding.bind(view)

    fun render(heroModel: ApiHeroModel, onClickListener: (ApiHeroModel) -> Unit) {
        binding.heroNameTV.text = heroModel.name
        //binding.publisherTV.text = heroModel.biography.publisher
        Glide.with(binding.imgCard.context)
            .load(heroModel.image.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imgCard)

        itemView.setOnClickListener {
            onClickListener(heroModel) }
    }
}