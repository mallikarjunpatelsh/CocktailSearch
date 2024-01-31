package com.wise.cocktail.ui.viewholder

import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wise.cocktail.databinding.LayoutCocktailItemBinding
import com.wise.cocktail.domain.model.Drink
import com.wise.cocktail.ui.fragment.IHomeCallback

class HomeCocktailViewholder(private val binding: LayoutCocktailItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(drink: Drink, callback: IHomeCallback?) {
        binding.drink = drink
        Glide.with(binding.root.context)
            .load(drink.strDrinkThumb)
            .into(binding.coaktailImageView)

        binding.root.setOnClickListener {
            callback?.onCocktailClick(drink)
        }
    }
}