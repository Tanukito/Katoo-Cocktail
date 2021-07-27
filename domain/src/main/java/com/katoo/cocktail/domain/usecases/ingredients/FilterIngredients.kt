package com.katoo.cocktail.domain.usecases.ingredients

import com.katoo.cocktail.domain.models.Error
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.BaseUseCase
import com.katoo.cocktail.domain.usecases.ingredients.FilterIngredients.Params

class FilterIngredients : BaseUseCase<Params, List<Ingredient>>() {

    override suspend fun doAction(params: Params?): Result<List<Ingredient>> {
        return params?.run {
            Result.Success(
                ingredients.filter { ingredient ->
                    ingredient.name.lowercase().contains(filter.lowercase())
                }
            )
        } ?: Result.Failure(Error.EmptyParams)
    }

    data class Params(val filter: String, val ingredients: List<Ingredient>)
}