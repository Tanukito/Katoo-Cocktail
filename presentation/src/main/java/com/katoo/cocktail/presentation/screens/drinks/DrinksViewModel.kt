package com.katoo.cocktail.presentation.screens.drinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.domain.usecases.drinks.GetDrinksByIngredient
import com.katoo.cocktail.presentation.mapper.toPresentation
import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DrinksViewModel(
    private val ingredient: String,
    private val getDrinksByIngredient: GetDrinksByIngredient,
    private val navigator: Navigator,
    coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(coroutineContext = coroutineContext) {
    private val drinksMLD = MutableLiveData<PresentationResult<List<Drink>>>()
    val drinksLD: LiveData<PresentationResult<List<Drink>>>
        get() = drinksMLD

    init {
        doGetDrinksByIngredient()
    }

    fun emptyRetryClicked() {
        doGetDrinksByIngredient()
    }

    private fun doGetDrinksByIngredient() {
        launch {
            drinksMLD.postValue(PresentationResult.Loading())
            val result = getDrinksByIngredient(GetDrinksByIngredient.Params(ingredient))
            drinksMLD.postValue(result.toPresentation())
        }
    }

    fun searchBarClicked() {
        navigator.goFromDrinksToSearch()
    }
}