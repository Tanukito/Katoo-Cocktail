package com.katoo.cocktail.domain.usecases.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.BaseUseCase
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients.Params

class GetIngredients(
    private val repository: IngredientsRepository
) : BaseUseCase<Params, List<Ingredient>>() {

    override suspend fun doAction(params: Params): Result<List<Ingredient>> {
        return repository.getIngredients()
    }

    class Params
}