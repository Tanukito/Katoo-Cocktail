package com.katoo.cocktail.data.repositories.drinks

import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.result.Result

interface DrinksRemoteDataSource {

    suspend fun getDrinksByIngredient(ingredient: String): Result<List<Drink>>
}