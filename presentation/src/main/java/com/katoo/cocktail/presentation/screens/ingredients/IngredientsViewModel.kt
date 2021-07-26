package com.katoo.cocktail.presentation.screens.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.result.toPresentationResult
import com.katoo.cocktail.presentation.screens.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IngredientsViewModel(
    private val getIngredients: GetIngredients,
    private val navigator: Navigator,
    coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(coroutineContext = coroutineContext) {
    private val ingredientsMLD = MutableLiveData<PresentationResult<List<Ingredient>>>()
    val ingredientsLD: LiveData<PresentationResult<List<Ingredient>>>
        get() = ingredientsMLD

    init {
        doGetIngredients()
    }

    fun emptyRetryClicked() {
        doGetIngredients()
    }

    private fun doGetIngredients() {
        launch {
            ingredientsMLD.postValue(PresentationResult.Loading())
            val ingredients = getIngredients()
            ingredientsMLD.postValue(ingredients.toPresentationResult())
        }
    }

    fun ingredientClicked(ingredient: Ingredient) {
        navigator.goFromIngredientsToDrinks()
    }
}