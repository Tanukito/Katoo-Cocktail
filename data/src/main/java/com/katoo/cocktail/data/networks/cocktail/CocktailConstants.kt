package com.katoo.cocktail.data.networks.cocktail

object CocktailConstants {
    const val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"

    const val ingredientsEndPoint = "list.php?i=list"

    const val drinksEndPoint = "filter.php"
    const val drinksIngredientParam = "i"

    const val imageIngredientParam = "{ingredient}"
    const val imageIngredient =
        "https://www.thecocktaildb.com/images/ingredients/$imageIngredientParam-Small.png"
}