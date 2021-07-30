package com.katoo.cocktail.domain.error

sealed class Error {
    object NoConnection: Error()
    object UnknownHost: Error()
    object EmptyBody: Error()
    object Unknown: Error()
}