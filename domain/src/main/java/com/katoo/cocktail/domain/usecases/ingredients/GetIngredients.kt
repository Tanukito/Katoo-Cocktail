package com.katoo.cocktail.domain.usecases.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.BaseUseCase

class GetIngredients(
    private val repository: IngredientsRepository
) : BaseUseCase<Nothing, List<Ingredient>>() {

    override fun doAction(params: Nothing?): Result<List<Ingredient>> {
        return repository.getIngredients()
    }
}