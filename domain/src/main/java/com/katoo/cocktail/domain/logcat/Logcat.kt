package com.katoo.cocktail.domain.logcat

import android.util.Log

object Logcat {
    private val TAG = "CocktailApp"

    fun d(message: String) {
        Log.d(TAG, message)
    }
}