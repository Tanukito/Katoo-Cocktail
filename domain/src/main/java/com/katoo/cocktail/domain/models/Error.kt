package com.katoo.cocktail.domain.models

sealed class Error {
    object EmptyParams: Error()
    object UnknownHost: Error()
    object EmptyBody: Error()
    object Unknown: Error()
}