package com.katoo.cocktail.domain.drinks

import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.DrinksRepository
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.drinks.GetDrinksByIngredient
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDrinksByIngredientUnitTest {

    @Mock
    private lateinit var repository: DrinksRepository

    private lateinit var getDrinksByIngredient: GetDrinksByIngredient

    @Before
    fun setup() {
        getDrinksByIngredient = GetDrinksByIngredient(repository)
    }

    @Test
    fun `given repository returns a list of drinks, when calling getDrinksByIngredient use case then returns the drinks`() {
        runBlocking {
            Mockito.`when`(repository.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Result.Success(
                    listOf(
                        Drink("i1", "i1", "i1"),
                        Drink("i2", "i2", "i2"),
                    )
                )
            )

            val result = getDrinksByIngredient(GetDrinksByIngredient.Params(ingredient = "i"))

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Drink("i1", "i1", "i1"),
                        Drink("i2", "i2", "i2"),
                    )
                ),
                result
            )
        }
    }

    @Test
    fun `given repository returns unknown error, when calling getIngredients use case then returns unknown error`() {
        runBlocking {
            Mockito.`when`(repository.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Result.Failure(Error.Unknown)
            )

            val result = getDrinksByIngredient(GetDrinksByIngredient.Params(ingredient = "i"))

            Assert.assertEquals(
                Result.Failure<Drink>(Error.Unknown),
                result
            )
        }
    }

}