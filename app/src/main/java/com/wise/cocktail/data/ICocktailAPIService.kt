package com.wise.cocktail.data

import android.util.Log
import com.wise.cocktail.util.AppConstants.URLConstants
import com.wise.cocktail.domain.model.Cocktails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Queue
import kotlin.random.Random

interface ICocktailAPIService {

    @GET(URLConstants.API_VERSION_V1 + URLConstants.SLASH + URLConstants.SEARCH_ENDPOINT)
    suspend fun getCocktails(@Query(URLConstants.QUERY_PARAM_S) value: String): Response<Cocktails>
}