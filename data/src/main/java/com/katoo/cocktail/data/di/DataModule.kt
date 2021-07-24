package com.katoo.cocktail.data.di

import com.katoo.cocktail.data.frameworks.CocktailRemoteFramework
import com.katoo.cocktail.data.repositories.ingredients.IngredientsDataRepository
import com.katoo.cocktail.data.repositories.ingredients.IngredientsRemoteDataSource
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import org.koin.dsl.module

val dataModule = module {
    single<IngredientsRepository> { IngredientsDataRepository(remoteDataSource = get()) }

    factory<IngredientsRemoteDataSource> { get<CocktailRemoteFramework>() }

    single { CocktailRemoteFramework() }
}