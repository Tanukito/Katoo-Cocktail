package com.katoo.cocktail.presentation.screens.ingredients


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import com.katoo.cocktail.presentation.anyGetIngredientsParams
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
class IngredientsViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getIngredients: GetIngredients

    @Mock
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var ingredientsObserver: Observer<PresentationResult<List<Ingredient>>>

    private lateinit var viewModel: IngredientsViewModel

    private fun setup() {
        viewModel = IngredientsViewModel(
            getIngredients = getIngredients,
            navigator = navigator,
            coroutineContext = Dispatchers.Unconfined
        )
    }

    @Test
    fun `given getIngredients returns ingredients, when initializing viewModel then ingredientsLD contains ingredients value`() {
        runBlocking {
            Mockito.`when`(getIngredients(anyGetIngredientsParams()))
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

    @Test
    fun `given a list of ingredients, clicking over one, navigates to drinks screen`() {
        runBlocking {
            Mockito.`when`(getIngredients.invoke(anyGetIngredientsParams()))
                .thenReturn(
                    Result.Success(
                        listOf(
                            Ingredient("i1", "i1")
                        )
                    )
                )

            setup()
            viewModel.ingredientClicked(Ingredient("i1", "i1"))

            Mockito.verify(navigator).goFromIngredientsToDrinks("i1")
        }
    }

}