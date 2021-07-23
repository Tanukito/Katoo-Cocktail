package com.katoo.cocktail

import android.app.Application
import com.katoo.cocktail.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CocktailApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CocktailApp)
            modules(presentationModule)
        }
    }
}