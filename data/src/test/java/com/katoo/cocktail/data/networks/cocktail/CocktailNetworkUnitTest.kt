package com.katoo.cocktail.data.networks.cocktail

import com.katoo.cocktail.data.handlers.ConnectivityHandler
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CocktailNetworkUnitTest {

    @Mock
    private lateinit var service: CocktailService

    @Mock
    private lateinit var generator: CocktailGenerator

    @Mock
    private lateinit var connectivity: ConnectivityHandler

    private lateinit var network: CocktailNetwork

    private val responseBody: ResponseBody
        get() = ByteArray(0).toResponseBody()

    @Before
    fun setup() {
        network = CocktailNetwork(service, generator, connectivity)
    }

    @Test
    fun `given connectivity returns there is no connection, when calling getIngredients function then returns no connection error`() {
        runBlocking {
            Mockito.`when`(connectivity.isConnected()).thenReturn(false)

            val result = network.getIngredients()

            Assert.assertEquals(
                Result.Failure<Ingredient>(Error.NoConnection),
                result
            )
        }
    }

    @Test
    fun `given service returns a list of ingredients, when calling getIngredients function then returns the ingredients`() {
        runBlocking {
            Mockito.`when`(connectivity.isConnected()).thenReturn(true)
            Mockito.`when`(generator.getIngredientImagePath(Mockito.anyString())).thenReturn("ip")
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

            val result = network.getIngredients()

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

    @Test
    fun `given service returns 500, when calling getIngredients function then returns unknown error`() {
        runBlocking {
            Mockito.`when`(connectivity.isConnected()).thenReturn(true)
            Mockito.`when`(service.getIngredients()).thenReturn(
                Response.error(500, responseBody)
            )

            val result = network.getIngredients()

            Assert.assertEquals(
                Result.Failure<Ingredient>(Error.Unknown),
                result
            )
        }
    }

    @Test
    fun `given service returns a list of drinks, when calling getDrinksByIngredient function then returns the drinks`() {
        runBlocking {
            Mockito.`when`(connectivity.isConnected()).thenReturn(true)
            Mockito.`when`(service.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Response.success(
                    CocktailResponse(
                        listOf(
                            DrinkResponse("1", "d1", "d1"),
                            DrinkResponse("2", "d2", "d2")
                        )
                    )
                )
            )

            val result = network.getDrinksByIngredient("i")

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Drink("1", "d1", "d1"),
                        Drink("2", "d2", "d2")
                    )
                ),
                result
            )
        }
    }

    @Test
    fun `given service returns 500, when calling getDrinksByIngredient function then returns unknown error`() {
        runBlocking {
            Mockito.`when`(connectivity.isConnected()).thenReturn(true)
            Mockito.`when`(service.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Response.error(500, responseBody)
            )

            val result = network.getDrinksByIngredient("i")

            Assert.assertEquals(
                Result.Failure<Drink>(Error.Unknown),
                result
            )
        }
    }

}