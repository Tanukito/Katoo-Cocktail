package com.katoo.cocktail.presentation.screens.ingredients


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.result.PresentationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IngredientsViewModelIntegrationTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var ingredientsRepository: IngredientsRepository

    private lateinit var getIngredients: GetIngredients

    @Mock
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var ingredientsObserver: Observer<PresentationResult<List<Ingredient>>>

    private lateinit var viewModel: IngredientsViewModel

    private fun setup() {
        getIngredients = GetIngredients(repository = ingredientsRepository)
        viewModel = IngredientsViewModel(
            getIngredients = getIngredients,
            navigator = navigator,
            coroutineContext = Dispatchers.Unconfined
        )
    }

    @Test
    fun `given ingredientsRepository returns ingredients, when initializing viewModel then ingredientsLD contains ingredients value`() {
        runBlocking {
            Mockito.`when`(ingredientsRepository.getIngredients())
                .thenReturn(
                    Result.Success(
                        listOf(
                            Ingredient("i1", "i1"),
                            Ingredient("i2", "i2"),
                        )
                    )
                )

            setup()
            viewModel.ingredientsLD.observeForever(ingredientsObserver)

            Mockito.verify(ingredientsObserver).onChanged(
                PresentationResult.Success(
                    listOf(
                        Ingredient("i1", "i1"),
                        Ingredient("i2", "i2"),
                    )

                )
            )
        }
    }

}