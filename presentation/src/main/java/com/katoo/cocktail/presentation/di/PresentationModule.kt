package com.katoo.cocktail.presentation.di

import com.katoo.cocktail.presentation.navigator.Navigator
import com.katoo.cocktail.presentation.navigator.NavigatorLifecycle
import com.katoo.cocktail.presentation.screens.drinks.DrinksViewModel
import com.katoo.cocktail.presentation.screens.ingredients.IngredientsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        IngredientsViewModel(
            getIngredients = get(),
            navigator = get(),
            coroutineContext = get()
        )
    }
    viewModel { (ingredient: String) ->
        DrinksViewModel(
            ingredient = ingredient,
            getDrinksByIngredient = get(),
            coroutineContext = get()
        )
    }

    single { Navigator(navigatorLifecycle = get()) }
    single { NavigatorLifecycle(app = androidApplication()) }

    single { Dispatchers.IO }
}