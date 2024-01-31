package com.wise.cocktail.data.repository

import com.wise.cocktail.data.datasource.ICocktailDatasource
import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.di.qualifier.RemoteDatasource
import com.wise.cocktail.domain.ICocktailRepository
import com.wise.cocktail.domain.model.Cocktails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(@RemoteDatasource private val cocktailDatasource: ICocktailDatasource):
    ICocktailRepository {
    override fun getCocktails(cocktailType: String): Flow<Resource<Cocktails>> = flow{
        cocktailDatasource.getCocktailsFromServer(cocktailType).collect {
            emit(it)
        }
    }

}