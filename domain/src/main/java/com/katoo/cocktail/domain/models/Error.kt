package com.katoo.cocktail.domain.models

sealed class Error {
    object EmptyParams: Error()
    object NoInternetConnection: Error()
    object Unknown: Error()
}