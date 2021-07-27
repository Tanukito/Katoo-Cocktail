package com.katoo.cocktail.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.domain.usecases.ingredients.FilterIngredients
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.result.toPresentationResult
import com.katoo.cocktail.presentation.screens.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val getIngredients: GetIngredients,
    private val filterIngredients: FilterIngredients,
    private val navigator: Navigator,
    coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(coroutineContext = coroutineContext) {
    private val ingredientsMLD = MutableLiveData<PresentationResult<List<Ingredient>>>()
    val ingredientsLD: LiveData<PresentationResult<List<Ingredient>>>
        get() = ingredientsMLD

    private var ingredients: List<Ingredient> = emptyList()

    init {
        doGetIngredients()
    }

    fun emptyRetryClicked() {
        doGetIngredients()
    }

    private fun doGetIngredients() {
        launch {
            ingredientsMLD.postValue(PresentationResult.Loading())
            val ingredientsResult = getIngredients()
            ingredients = ingredientsResult.toIngredients()
            ingredientsMLD.postValue(ingredientsResult.toPresentationResult())
        }
    }

    private fun Result<List<Ingredient>>.toIngredients(): List<Ingredient> {
        return when (this) {
            is Result.Failure -> emptyList()
            is Result.Success -> data
        }
    }

    fun searchChanged(text: String) {
        launch {
            val result = filterIngredients(FilterIngredients.Params(text, ingredients))
            ingredientsMLD.postValue(result.toPresentationResult())
        }
    }

    fun ingredientClicked(ingredient: Ingredient) {
        navigator.goFromSearchToDrinks(ingredient.name)
    }
}