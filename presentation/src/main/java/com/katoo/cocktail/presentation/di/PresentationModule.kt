package com.katoo.cocktail.presentation.di

import com.katoo.cocktail.presentation.screens.ingredients.IngredientsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { IngredientsViewModel(getIngredients = get()) }
}