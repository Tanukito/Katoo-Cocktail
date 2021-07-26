package com.katoo.cocktail.presentation.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseNavigator(
    private val navigatorLifecycle: NavigatorLifecycle
) {

    private val currentActivity: AppCompatActivity?
        get() = navigatorLifecycle.activity

    protected fun goTo(directions: NavDirections) {
        currentActivity?.supportFragmentManager?.primaryNavigationFragment?.findNavController()
            ?.navigate(directions)
    }
}