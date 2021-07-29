package com.katoo.cocktail.data.repositories.drinks

import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.models.Error
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
class DrinksDataRepositoryUnitTest {

    @Mock
    private lateinit var remoteDataSource: DrinksRemoteDataSource

    private lateinit var repository: DrinksDataRepository

    @Before
    fun setup() {
        repository = DrinksDataRepository(remoteDataSource)
    }

    @Test
    fun `given remote returns a list of drinks, when calling getDrinksByIngredient function then returns the drinks`() {
        runBlocking {
            Mockito.`when`(remoteDataSource.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Result.Success(
                    listOf(
                        Drink("d1", "d1", "d1"),
                        Drink("d2", "d2", "d2")
                    )
                )
            )

            val result = repository.getDrinksByIngredient("i")

            Assert.assertEquals(
                Result.Success(
                    listOf(
                        Drink("d1", "d1", "d1"),
                        Drink("d2", "d2", "d2")
                    )
                ),
                result
            )
        }
    }

    @Test
    fun `given remote returns unknown error, when calling getDrinksByIngredient function then returns unknown error`() {
        runBlocking {
            Mockito.`when`(remoteDataSource.getDrinksByIngredient(Mockito.anyString())).thenReturn(
                Result.Failure(Error.Unknown)
            )

            val result = repository.getDrinksByIngredient("i")

            Assert.assertEquals(
                Result.Failure<Drink>(Error.Unknown),
                result
            )
        }
    }

}