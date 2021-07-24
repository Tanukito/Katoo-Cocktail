package com.katoo.cocktail.domain.repositories

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result

interface IngredientsRepository {

    fun getIngredients(): Result<List<Ingredient>>
}