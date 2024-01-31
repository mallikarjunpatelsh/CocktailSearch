package com.wise.cocktail.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wise.cocktail.R
import com.wise.cocktail.databinding.FragmentCoktailDetailsBinding
import com.wise.cocktail.domain.model.Drink
import com.wise.cocktail.util.AppConstants

class CoktailDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentCoktailDetailsBinding
    private var drink: Drink? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!::binding.isInitialized) {
            binding = FragmentCoktailDetailsBinding.inflate(layoutInflater)
        }
        return binding.root
    }


    /**
     * Get bundle parameter
     * extract the drink object in bundle
     */
    override fun getBundleParameter() {
        try {
            arguments?.let {
                drink = it.getParcelable(AppConstants.BundleKeys.DRINK)
            }
        } catch (e: Exception) {
            showToast(getString(R.string.something_went_wrong))
            findNavController().popBackStack()
        }
    }

    override fun setObservers() {

    }

    /**
     * Init views
     * Bind the details to view
     */
    override fun initViews() {
        drink?.let {
            binding.drink = it
            Glide.with(binding.ivCocktailDetail)
                .load(it.strDrinkThumb)
                .into(binding.ivCocktailDetail)
        }
    }
}