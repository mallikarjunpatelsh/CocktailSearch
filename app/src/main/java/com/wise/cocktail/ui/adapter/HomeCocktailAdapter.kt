package com.wise.cocktail.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wise.cocktail.databinding.LayoutCocktailItemBinding
import com.wise.cocktail.databinding.LayoutResturantItemBinding
import com.wise.cocktail.domain.model.Drink
import com.wise.cocktail.ui.fragment.IHomeCallback
import com.wise.cocktail.ui.viewholder.HomeCocktailViewholder
import com.wise.cocktail.ui.viewholder.HomeRestuarantViewholder

/**
 * Home cocktail adapter
 * Adapter to load the cocktails list
 * This binds two viewtypes
 */
class HomeCocktailAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var cocktailList: List<Drink>? = null
    private var callback: IHomeCallback? = null

    fun setCocktailDrinks(cocktailList: List<Drink>) {
        this.cocktailList = cocktailList
        notifyDataSetChanged()
    }

    fun setHomeCallback(callback: IHomeCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RESTUARANT_VIEWTYPE -> {
                HomeRestuarantViewholder(LayoutResturantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

            DRINKS_VIEWTYPE -> {
                HomeCocktailViewholder(LayoutCocktailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            } else -> {
                HomeRestuarantViewholder(LayoutResturantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return cocktailList?.size?:1
    }

    override fun getItemViewType(position: Int): Int {
        if (itemCount != 0 && position != 0 && cocktailList?.get(position-1) != null) {
            return DRINKS_VIEWTYPE
        }
        return RESTUARANT_VIEWTYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            RESTUARANT_VIEWTYPE -> {
            }

            DRINKS_VIEWTYPE -> {
                cocktailList?.get(position)?.let { (holder as HomeCocktailViewholder).bind(it, callback) }
            }
        }
    }

    companion object {
        const val RESTUARANT_VIEWTYPE = 1
        const val DRINKS_VIEWTYPE = 2
    }
}