package com.wise.cocktail.data.datasource

import android.content.Context
import com.wise.cocktail.data.datasource.base.BaseDataSource
import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.data.ICocktailAPIService
import com.wise.cocktail.domain.model.Cocktails
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailDatasourceImpl @Inject constructor(@ApplicationContext private val context: Context, private val cocktailAPIService: ICocktailAPIService): ICocktailDatasource, BaseDataSource(context) {
    override fun getCocktailsFromServer(cocktailType: String): Flow<Resource<Cocktails>> = getResult {
        cocktailAPIService.getCocktails(cocktailType)
    }
}