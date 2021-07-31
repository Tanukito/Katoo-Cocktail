package com.katoo.cocktail.domain.ingredients

import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result
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
class GetIngredientsUnitTest {

    @Mock
    private lateinit var repository: IngredientsRepository

    private lateinit var getIngredients: GetIngredients

    @Before
    fun setup() {
        getIngredients = GetIngredients(repository)
    }

    @Test
    fun `given repository returns a list of ingredients, when calling getIngredients use case then returns the ingredients`() {
        runBlocking {
            Mockito.`when`(repository.getIngredients()).thenReturn(
                Result.Success(
                    listOf(
                        Ingredient("i1", "i1"),
                        Ingredient("i2", "i2"),
                    )
                )
            )

            val result = getIngredients(GetIngredients.Params())

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Ingredient("i1", "i1"),
                        Ingredient("i2", "i2"),
                    )
                ),
                result
            )
        }
    }

    @Test
    fun `given repository returns unknown error, when calling getIngredients use case then returns unknown error`() {
        runBlocking {
            Mockito.`when`(repository.getIngredients()).thenReturn(
                Result.Failure(Error.Unknown)
            )

            val result = getIngredients(GetIngredients.Params())

            Assert.assertEquals(
                Result.Failure<Drink>(Error.Unknown),
                result
            )
        }
    }

}