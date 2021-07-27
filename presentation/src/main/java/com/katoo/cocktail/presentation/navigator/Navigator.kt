package com.katoo.cocktail.presentation.navigator

import com.katoo.cocktail.presentation.screens.drinks.DrinksFragmentDirections
import com.katoo.cocktail.presentation.screens.ingredients.IngredientsFragmentDirections
import com.katoo.cocktail.presentation.screens.search.SearchFragmentDirections

class Navigator(
    navigatorLifecycle: NavigatorLifecycle
) : BaseNavigator(navigatorLifecycle) {

    fun goFromIngredientsToDrinks(ingredient: String) {
        goTo(IngredientsFragmentDirections.actionIngredientsFragmentToDrinksFragment(ingredient))
    }

    fun goFromDrinksToSearch() {
        goTo(DrinksFragmentDirections.actionDrinksFragmentToSearchFragment())
    }

    fun goFromSearchToDrinks(ingredient: String) {
        goTo(SearchFragmentDirections.actionSearchFragmentToDrinksFragment(ingredient))
    }
}