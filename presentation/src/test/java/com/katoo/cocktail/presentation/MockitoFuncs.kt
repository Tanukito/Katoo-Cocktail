package com.katoo.cocktail.presentation

import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import org.mockito.Mockito

fun anyGetIngredientsParams(): GetIngredients.Params = anyObject(GetIngredients.Params::class.java)

private fun <T> anyObject(type: Class<T>): T = Mockito.any(type)
