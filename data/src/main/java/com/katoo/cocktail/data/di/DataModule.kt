package com.katoo.cocktail.data.di

import com.katoo.cocktail.data.frameworks.cocktail.CocktailConstants
import com.katoo.cocktail.data.frameworks.cocktail.CocktailNetwork
import com.katoo.cocktail.data.frameworks.cocktail.CocktailService
import com.katoo.cocktail.data.repositories.drinks.DrinksDataRepository
import com.katoo.cocktail.data.repositories.drinks.DrinksRemoteDataSource
import com.katoo.cocktail.data.repositories.ingredients.IngredientsDataRepository
import com.katoo.cocktail.data.repositories.ingredients.IngredientsRemoteDataSource
import com.katoo.cocktail.domain.repositories.DrinksRepository
import com.katoo.cocktail.domain.repositories.IngredientsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    single<IngredientsRepository> { IngredientsDataRepository(remoteDataSource = get()) }
    single<DrinksRepository> { DrinksDataRepository(remoteDataSource = get()) }

    single<IngredientsRemoteDataSource> { get<CocktailNetwork>() }
    single<DrinksRemoteDataSource> { get<CocktailNetwork>() }

    single { CocktailNetwork(service = get()) }

    single { get<Retrofit>().create(CocktailService::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(CocktailConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .build()
    }
}