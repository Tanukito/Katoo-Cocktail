package com.katoo.cocktail.data.repositories.drinks

import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.repositories.DrinksRepository
import com.katoo.cocktail.domain.result.Result

class DrinksDataRepository(
    private val remoteDataSource: DrinksRemoteDataSource
) : DrinksRepository {

    override suspend fun getDrinksByIngredient(ingredient: String): Result<List<Drink>> {
        return remoteDataSource.getDrinksByIngredient(ingredient)
    }
}