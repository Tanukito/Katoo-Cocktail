package com.katoo.cocktail.presentation.result

import com.katoo.cocktail.domain.error.Error

sealed class PresentationResult<out Model> {
    class Loading<out Model> : PresentationResult<Model>()
    data class Failure<out Model>(val error: Error) : PresentationResult<Model>()
    data class Success<out Model>(val data: Model) : PresentationResult<Model>()
}