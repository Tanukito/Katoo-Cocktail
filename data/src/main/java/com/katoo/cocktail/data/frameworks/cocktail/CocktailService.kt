package com.katoo.cocktail.data.frameworks.cocktail

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET(CocktailConstants.ingredientsEndPoint)
    suspend fun getIngredients(): Response<CocktailResponse<IngredientResponse>>

    @GET(CocktailConstants.drinksEndPoint)
    suspend fun getDrinksByIngredient(
        @Query(CocktailConstants.drinksIngredientParam) ingredient: String
    ): Response<CocktailResponse<DrinkResponse>>
}