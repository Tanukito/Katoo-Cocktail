package com.katoo.cocktail.data.repositories.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result

class IngredientsDataRepository(
    private val remoteDataSource: IngredientsRemoteDataSource
) : IngredientsRepository {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        return remoteDataSource.getIngredients()
    }
}