package com.katoo.cocktail.presentation.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val imm =
        context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}