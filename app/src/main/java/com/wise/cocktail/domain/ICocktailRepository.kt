package com.wise.cocktail.domain

import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.domain.model.Cocktails
import kotlinx.coroutines.flow.Flow

interface ICocktailRepository {
    fun getCocktails(cocktailType: String): Flow<Resource<Cocktails>>
}