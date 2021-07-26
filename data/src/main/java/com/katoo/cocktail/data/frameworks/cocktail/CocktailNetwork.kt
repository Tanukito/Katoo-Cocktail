package com.katoo.cocktail.data.frameworks.cocktail

import com.katoo.cocktail.data.frameworks.BaseNetwork
import com.katoo.cocktail.data.repositories.drinks.DrinksRemoteDataSource
import com.katoo.cocktail.data.repositories.ingredients.IngredientsRemoteDataSource
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result

class CocktailNetwork(
    private val service: CocktailService
) : BaseNetwork(), IngredientsRemoteDataSource, DrinksRemoteDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        return doCall(
            request = {
                service.getIngredients()
            },
            map = { ingredients ->
                ingredients.map { ingredient ->
                    Ingredient(
                        name = ingredient.strIngredient1,
                        imagePath = CocktailConstants.imageIngredient.replace(
                            CocktailConstants.imageIngredientParam,
                            ingredient.strIngredient1
                        )
                    )
                }
            }
        )
    }

    override suspend fun getDrinksByIngredient(ingredient: String): Result<List<Drink>> {
        return doCall(
            request = {
                service.getDrinksByIngredient(ingredient)
            },
            map = { drinks ->
                drinks.map { drink ->
                    Drink(
                        id = drink.idDrink,
                        description = drink.strDrink,
                        imagePath = drink.strDrinkThumb
                    )
                }
            }
        )
    }
}