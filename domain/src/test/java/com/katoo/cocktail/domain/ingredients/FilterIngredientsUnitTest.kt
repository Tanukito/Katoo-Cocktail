package com.katoo.cocktail.domain.ingredients

import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.ingredients.FilterIngredients
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilterIngredientsUnitTest {

    private lateinit var filterIngredients: FilterIngredients

    @Before
    fun setup() {
        filterIngredients = FilterIngredients()
    }

    @Test
    fun `given a list, when filtering some existing value, returns a list containing that value`() {
        runBlocking {
            val list = listOf(
                Ingredient("a"),
                Ingredient("b"),
            )

            val result = filterIngredients(FilterIngredients.Params("a", list))

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Ingredient("a")
                    )
                ),
                result
            )
        }
    }

    @Test
    fun `given a list, when filtering some non existing value, returns an empty list`() {
        runBlocking {
            val list = listOf(
                Ingredient("a"),
                Ingredient("b"),
            )

            val result = filterIngredients(FilterIngredients.Params("c", list))

            Assert.assertEquals(
                Result.Success(emptyList<Ingredient>()),
                result
            )
        }
    }

}