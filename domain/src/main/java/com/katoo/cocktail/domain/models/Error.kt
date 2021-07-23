package com.katoo.cocktail.domain.models

sealed class Error {
    object NoInternetConnection: Error()
    object Unknown: Error()
}