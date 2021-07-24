package com.katoo.cocktail.presentation.screens.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.result.toPresentationResult
import com.katoo.cocktail.presentation.screens.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IngredientsViewModel(
    getIngredients: GetIngredients,
    coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(coroutineContext = coroutineContext) {
    private val ingredientsMLD = MutableLiveData<PresentationResult<List<Ingredient>>>()
    val ingredientsLD: LiveData<PresentationResult<List<Ingredient>>>
        get() = ingredientsMLD

    init {
        launch {
            ingredientsMLD.postValue(PresentationResult.Loading())
            val ingredients = getIngredients()
            ingredientsMLD.postValue(ingredients.toPresentationResult())
        }
    }
}