package com.katoo.cocktail.data.repositories.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result

interface IngredientsRemoteDataSource {

    fun getIngredients(): Result<List<Ingredient>>
}