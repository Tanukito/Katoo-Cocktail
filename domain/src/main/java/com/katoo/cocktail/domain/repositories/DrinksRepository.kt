package com.katoo.cocktail.domain.repositories

import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.result.Result

interface DrinksRepository {

    suspend fun getDrinksByIngredient(ingredient: String): Result<List<Drink>>
}