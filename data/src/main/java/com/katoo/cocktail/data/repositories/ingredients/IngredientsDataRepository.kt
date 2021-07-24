package com.katoo.cocktail.data.repositories.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result

class IngredientsDataRepository: IngredientsRepository {

    override fun getIngredients(): Result<List<Ingredient>> {
        return Result.Success(listOf(
            Ingredient(name = "Light rum", imagePath = "https://www.thecocktaildb.com/images/ingredients/Light rum-Small.png"),
            Ingredient(name = "Applejack", imagePath = "https://www.thecocktaildb.com/images/ingredients/Applejack-Small.png"),
        ))
    }
}