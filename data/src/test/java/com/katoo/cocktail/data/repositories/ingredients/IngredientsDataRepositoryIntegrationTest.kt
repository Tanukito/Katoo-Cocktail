package com.katoo.cocktail.data.repositories.ingredients

import com.katoo.cocktail.data.frameworks.cocktail.*
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Error
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class IngredientsDataRepositoryIntegrationTest {

    @Mock
    private lateinit var service: CocktailService

    @Mock
    private lateinit var generator: CocktailGenerator

    private lateinit var network: CocktailNetwork

    private lateinit var repository: IngredientsDataRepository

    @Before
    fun setup() {
        network = CocktailNetwork(service, generator)
        repository = IngredientsDataRepository(network)
    }

    @Test
    fun `given services returns a list of ingredients, when calling getIngredients function then returns the ingredients`() {
        runBlocking {
            Mockito.`when`(service.getIngredients()).thenReturn(
                Response.success(
                    CocktailResponse(
                        listOf(
                            IngredientResponse("i1"),
                            IngredientResponse("i2")
                        )
                    )
                )
            )
            Mockito.`when`(generator.getIngredientImagePath(Mockito.anyString())).thenReturn(
                "ip"
            )

            val result = repository.getIngredients()

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Ingredient("i1", "ip"),
                        Ingredient("i2", "ip"),
                    )
                ),
                result
            )
        }
    }

}