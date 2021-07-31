package com.katoo.cocktail.data.networks.cocktail

import com.katoo.cocktail.data.networks.BaseNetwork
import com.katoo.cocktail.data.handlers.ConnectivityHandler
import com.katoo.cocktail.data.mappers.toDomain
import com.katoo.cocktail.data.repositories.drinks.DrinksRemoteDataSource
import com.katoo.cocktail.data.repositories.ingredients.IngredientsRemoteDataSource
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result

class CocktailNetwork(
    private val service: CocktailService,
    private val generator: CocktailGenerator,
    connectivity: ConnectivityHandler
) : BaseNetwork(connectivity), IngredientsRemoteDataSource, DrinksRemoteDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        return doCall(
            request = {
                service.getIngredients()
            },
            map = { ingredients ->
                ingredients.toDomain(generator)
            }
        )
    }

    override suspend fun getDrinksByIngredient(ingredient: String): Result<List<Drink>> {
        return doCall(
            request = {
                service.getDrinksByIngredient(ingredient)
            },
            map = { drinks ->
                drinks.toDomain()
            }
        )
    }
}