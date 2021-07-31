package com.katoo.cocktail.presentation.di

import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.navigator.NavigatorLifecycle
import com.katoo.cocktail.presentation.screens.drinks.DrinksViewModel
import com.katoo.cocktail.presentation.screens.ingredients.IngredientsViewModel
import com.katoo.cocktail.presentation.screens.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { IngredientsViewModel(getIngredients = get(), navigator = get()) }
    viewModel { (ingredient: String) ->
        DrinksViewModel(
            ingredient = ingredient,
            getDrinksByIngredient = get(),
            navigator = get()
        )
    }
    viewModel {
        SearchViewModel(
            getIngredients = get(),
            filterIngredients = get(),
            navigator = get()
        )
    }

    single { Navigator(navigatorLifecycle = get()) }
    single { NavigatorLifecycle(app = androidApplication()) }
}