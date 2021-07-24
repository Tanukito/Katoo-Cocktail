package com.katoo.cocktail.data.frameworks.cocktail

data class CocktailResponse<ResponseType>(
    val drinks: List<ResponseType>
)

data class IngredientResponse(
    val strIngredient1: String = ""
)

data class DrinkResponse(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)