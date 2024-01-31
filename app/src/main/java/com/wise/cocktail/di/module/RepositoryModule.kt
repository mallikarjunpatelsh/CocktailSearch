package com.wise.cocktail.di.module

import com.wise.cocktail.data.repository.CocktailRepositoryImpl
import com.wise.cocktail.di.qualifier.Repository
import com.wise.cocktail.domain.ICocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    @Repository
    fun bindsWeatherRepository(weatherRepositoryImpl: CocktailRepositoryImpl): ICocktailRepository
}