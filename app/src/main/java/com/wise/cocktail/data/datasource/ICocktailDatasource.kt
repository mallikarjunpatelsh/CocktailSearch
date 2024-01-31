package com.wise.cocktail.data.datasource

import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.domain.model.Cocktails
import kotlinx.coroutines.flow.Flow

interface ICocktailDatasource {
    fun getCocktailsFromServer(cocktailType: String): Flow<Resource<Cocktails>>
}