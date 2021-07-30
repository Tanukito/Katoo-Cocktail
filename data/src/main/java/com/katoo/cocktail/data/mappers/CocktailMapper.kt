package com.katoo.cocktail.data.mappers

import com.katoo.cocktail.data.frameworks.cocktail.CocktailGenerator
import com.katoo.cocktail.data.frameworks.cocktail.DrinkResponse
import com.katoo.cocktail.data.frameworks.cocktail.IngredientResponse
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient

fun IngredientResponse.toDomain(generator: CocktailGenerator): Ingredient {
    return Ingredient(
        name = strIngredient1,
        imagePath = generator.getIngredientImagePath(strIngredient1)
    )
}

fun DrinkResponse.toDomain(): Drink {
    return Drink(
        id = idDrink,
        description = strDrink,
        imagePath = strDrinkThumb
    )
}