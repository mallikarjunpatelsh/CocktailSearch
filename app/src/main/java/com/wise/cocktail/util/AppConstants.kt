package com.wise.cocktail.util

sealed class AppConstants {
    class URLConstants: AppConstants() {
        companion object {
            const val API_BASE_URL = "https://www.thecocktaildb.com/api/json/"
            const val API_VERSION_V1 = "v1"
            const val QUERY_PARAM_S = "s"
            const val SLASH = "/"
            const val SEARCH_ENDPOINT = "1/search.php"
        }
    }

    class NetworkConstants: AppConstants() {
        companion object {
            const val DEFAULT_ERROR_CODE = -1
        }
    }

    class BundleKeys: AppConstants() {
        companion object {
            const val DRINK = "drink"
        }
    }
}
