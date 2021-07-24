package com.katoo.cocktail.domain.result

import com.katoo.cocktail.domain.models.Error

sealed class Result<out Model> {
    data class Failure<out Model>(val error: Error) : Result<Model>()
    data class Success<out Model>(val data: Model) : Result<Model>()
}