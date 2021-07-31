package com.katoo.cocktail.data.mappers

import com.katoo.cocktail.data.networks.cocktail.CocktailGenerator
import com.katoo.cocktail.data.networks.cocktail.DrinkResponse
import com.katoo.cocktail.data.networks.cocktail.IngredientResponse
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient

fun List<IngredientResponse>.toDomain(generator: CocktailGenerator): List<Ingredient> {
    return map { ingredient ->
        ingredient.toDomain(generator)
    }
}

fun IngredientResponse.toDomain(generator: CocktailGenerator): Ingredient {
    return Ingredient(
        name = strIngredient1,
        imagePath = generator.getIngredientImagePath(strIngredient1)
    )
}

fun List<DrinkResponse>.toDomain(): List<Drink> {
    return map { drink ->
        drink.toDomain()
    }
}

fun DrinkResponse.toDomain(): Drink {
    return Drink(
        id = idDrink,
        description = strDrink,
        imagePath = strDrinkThumb
    )
}