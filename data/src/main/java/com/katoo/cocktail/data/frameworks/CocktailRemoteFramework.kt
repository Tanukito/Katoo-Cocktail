package com.katoo.cocktail.data.frameworks

import com.katoo.cocktail.data.repositories.ingredients.IngredientsRemoteDataSource
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result

class CocktailRemoteFramework: IngredientsRemoteDataSource {

    override fun getIngredients(): Result<List<Ingredient>> {
        return Result.Success(
            listOf(
                Ingredient(
                    name = "Light rum",
                    imagePath = "https://www.thecocktaildb.com/images/ingredients/Light rum-Small.png"
                ),
                Ingredient(
                    name = "Applejack",
                    imagePath = "https://www.thecocktaildb.com/images/ingredients/Applejack-Small.png"
                ),
            )
        )
    }
}