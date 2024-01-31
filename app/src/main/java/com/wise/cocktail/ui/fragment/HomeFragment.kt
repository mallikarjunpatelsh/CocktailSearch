package com.wise.cocktail.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.R
import com.wise.cocktail.databinding.FragmentHomeBinding
import com.wise.cocktail.domain.model.Drink
import com.wise.cocktail.ui.adapter.HomeCocktailAdapter
import com.wise.cocktail.ui.viewmodel.HomeViewmodel
import com.wise.cocktail.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewmodel: HomeViewmodel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val cocktailAdapter by lazy { HomeCocktailAdapter() }
    private val homeCallback = object : IHomeCallback {
        override fun onCocktailClick(drink: Drink) {
            try {
                val bundle = Bundle()
                bundle.putParcelable(AppConstants.BundleKeys.DRINK, drink)
                findNavController().navigate(R.id.coktailDetailsFragment, bundle)
            } catch (e: Exception) {
                showToast(getString(R.string.something_went_wrong))
            }
        }
    }

    private val editorAction = TextView.OnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            cocktailAdapter.setCocktailDrinks(emptyList())
            viewmodel.fetchCocktail(binding.searchEditext.text.toString())
            val imm: InputMethodManager? = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
            imm?.hideSoftInputFromWindow(binding.searchEditext.windowToken, 0)
        }
        true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (!::binding.isInitialized) {
            binding = FragmentHomeBinding.inflate(layoutInflater)
            viewmodel.fetchCocktail("")
        }
        return binding.root
    }

    override fun getBundleParameter() {

    }

    /**
     * Set observers
     * collecting the cocktail details
     */
    override fun setObservers() {
        lifecycleScope.launch {
            viewmodel.searchedCocktails.collect {
                withContext(Dispatchers.Main) {
                    setLoadingState(it.state,it.status, it.message)
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            it.data?.drinks?.let { it1 -> cocktailAdapter.setCocktailDrinks(it1) }
                        }
                        Resource.Status.ERROR -> {
                            showToast(it.message)
                        } else -> {}
                    }
                }
            }
        }
    }

    /**
     * Init views
     * set the adapter to recyclerview
     */
    override fun initViews() {
        val layoutManager = GridLayoutManager(context, 2)
        (layoutManager).spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                try {
                    if (cocktailAdapter.getItemViewType(position) == HomeCocktailAdapter.RESTUARANT_VIEWTYPE) {
                        return 2
                    }
                    return 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return 1
            }
        }
        binding.searchEditext.setOnEditorActionListener(editorAction)
        cocktailAdapter.setHomeCallback(homeCallback)
        binding.rvCocktails.apply {
            adapter = cocktailAdapter
            this.layoutManager = layoutManager
        }
    }
}

interface IHomeCallback {
    fun onCocktailClick(drink: Drink)
}