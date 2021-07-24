package com.katoo.cocktail.domain.di

import com.katoo.cocktail.domain.usecases.drinks.GetDrinksByIngredient
import com.katoo.cocktail.domain.usecases.ingredients.GetIngredients
import org.koin.dsl.module

val domainModule = module {
    factory { GetIngredients(repository = get()) }
    factory { GetDrinksByIngredient(repository = get()) }
}