package com.katoo.cocktail.domain.usecases.drinks

import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.repositories.DrinksRepository
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.BaseUseCase
import com.katoo.cocktail.domain.usecases.drinks.GetDrinksByIngredient.Params

class GetDrinksByIngredient(
    private val repository: DrinksRepository
) : BaseUseCase<Params, List<Drink>>() {

    override suspend fun doAction(params: Params): Result<List<Drink>> {
        return repository.getDrinksByIngredient(params.ingredient)
    }

    data class Params(val ingredient: String)
}