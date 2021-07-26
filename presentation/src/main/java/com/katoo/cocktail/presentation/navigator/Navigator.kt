package com.katoo.cocktail.presentation.navigator

import com.katoo.cocktail.presentation.screens.ingredients.IngredientsFragmentDirections

class Navigator(
    navigatorLifecycle: NavigatorLifecycle
) : BaseNavigator(navigatorLifecycle) {

    fun goFromIngredientsToDrinks(ingredient: String) {
        goTo(IngredientsFragmentDirections.actionIngredientsFragmentToDrinksFragment(ingredient))
    }
}