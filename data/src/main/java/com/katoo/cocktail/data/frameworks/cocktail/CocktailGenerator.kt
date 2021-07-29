package com.katoo.cocktail.data.frameworks.cocktail

class CocktailGenerator {

    fun getIngredientImagePath(ingredientName: String): String {
        return CocktailConstants.imageIngredient.replace(
            CocktailConstants.imageIngredientParam,
            ingredientName
        )
    }
}