package com.wise.cocktail.di.module

import com.wise.cocktail.data.datasource.ICocktailDatasource
import com.wise.cocktail.data.datasource.CocktailDatasourceImpl
import com.wise.cocktail.di.qualifier.RemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DatasourceModule {

    @Binds
    @Singleton
    @RemoteDatasource
    fun bindsWeatherDatasource(cocktailDatasourceImpl: CocktailDatasourceImpl): ICocktailDatasource
}