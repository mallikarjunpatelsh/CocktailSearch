package com.wise.cocktail.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wise.cocktail.databinding.LayoutResturantItemBinding

class HomeRestuarantViewholder(private val binding: LayoutResturantItemBinding): RecyclerView.ViewHolder(binding.root) {

    init {
        Glide.with(binding.root.context)
            .load("https://lh3.googleusercontent.com/p/AF1QipPsmLOHjo2EQmesQGoP2ptLtPMXe9StIqpAK8s3=s1360-w1360-h1020")
            .into(binding.restuarantImageView)
    }
}