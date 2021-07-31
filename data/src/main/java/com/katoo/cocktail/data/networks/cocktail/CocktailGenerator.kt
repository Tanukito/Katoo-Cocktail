package com.katoo.cocktail.data.networks.cocktail

class CocktailGenerator {

    fun getIngredientImagePath(ingredientName: String): String {
        return CocktailConstants.imageIngredient.replace(
            CocktailConstants.imageIngredientParam,
            ingredientName
        )
    }
}