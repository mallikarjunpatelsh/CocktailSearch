package com.wise.cocktail.data.datasource.base

import android.content.Context
import android.util.Log
import com.cocktail.searchapp.data.datasource.base.Resource
import com.wise.cocktail.R
import com.wise.cocktail.util.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseDataSource(private val context: Context) {

    /**
     * This function will make it separate success and failure cases as well as handle to exceptions
     */
    protected fun <T> getResult(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body: T? = response.body()
                if (body != null) {
                    emit(Resource.success(body, response.message(), response.code(), false))
                } else {
                    Log.d("baseDatasource : ", "exception $")
                    emit(error(response.body(), context.getString(R.string.something_went_wrong), response.code()))
                }
            } else {
                Log.d("baseDatasource : ", "exception $")
                emit(error(response.body(), context.getString(R.string.something_went_wrong), response.code()))
            }
        } catch (e: Exception) {
            Log.d("baseDatasource : ", "exception $e")
                emit(error(message = context.getString(R.string.cannot_connect_to_server), code = AppConstants.NetworkConstants.DEFAULT_ERROR_CODE))
        }
    }


    private fun <T> error(body: T? = null, message: String, code: Int): Resource<T> {
        return Resource.error(body, message, code, false)
    }
}