package com.katoo.cocktail.data.repositories.ingredients

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

@RunWith(MockitoJUnitRunner::class)
class IngredientsDataRepositoryUnitTest {

    @Mock
    private lateinit var remoteDataSource: IngredientsRemoteDataSource

    private lateinit var repository: IngredientsDataRepository

    @Before
    fun setup() {
        repository = IngredientsDataRepository(remoteDataSource)
    }

    @Test
    fun `given remote returns a list of ingredients, when calling getIngredients function then returns the ingredients`() {
        runBlocking {
            Mockito.`when`(remoteDataSource.getIngredients()).thenReturn(
                Result.Success(
                    listOf(
                        Ingredient("i1", "i1"),
                        Ingredient("i2", "i2"),
                    )
                )
            )

            val result = repository.getIngredients()

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
    fun `given remote returns unknown error, when calling getIngredients function then returns unknown error`() {
        runBlocking {
            Mockito.`when`(remoteDataSource.getIngredients()).thenReturn(
                Result.Failure(Error.Unknown)
            )

            val result = repository.getIngredients()

            Assert.assertEquals(
                Result.Failure<Drink>(Error.Unknown),
                result
            )
        }
    }

}