package com.wise.cocktail.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink(
    val dateModified: String?,
    val idDrink: String?,
    val strAlcoholic: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: String?,
    val strDrink: String?,
    val strDrinkThumb: String?,
    val strGlass: String?,
    val strIBA: String?,
    val strInstructions: String?,
    val strMeasure1: String?
) : Parcelable