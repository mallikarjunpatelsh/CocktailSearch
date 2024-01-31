package com.wise.cocktail.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.di.qualifier.Repository
import com.wise.cocktail.domain.ICocktailRepository
import com.wise.cocktail.domain.model.Cocktails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(@Repository private val repository: ICocktailRepository): ViewModel() {

    private val _searchedCoktails = MutableStateFlow<Resource<Cocktails>>(Resource.loading(true))
    val searchedCocktails = _searchedCoktails.asStateFlow()
    fun fetchCocktail(coktailType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchedCoktails.emit(Resource.loading(true))
            repository.getCocktails(coktailType).collect {
                Log.d("cocktail response:", "$it")
                _searchedCoktails.emit(it)
            }
        }
    }
}