package com.katoo.cocktail

import android.app.Application
import com.katoo.cocktail.data.di.dataModule
import com.katoo.cocktail.domain.di.domainModule
import com.katoo.cocktail.presentation.di.presentationModule
import com.katoo.cocktail.presentation.navigator.NavigatorLifecycle
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("UNUSED")
class CocktailApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initNavigator()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CocktailApp)
            modules(presentationModule, domainModule, dataModule)
        }
    }

    private fun initNavigator() {
        get<NavigatorLifecycle>()
    }
}