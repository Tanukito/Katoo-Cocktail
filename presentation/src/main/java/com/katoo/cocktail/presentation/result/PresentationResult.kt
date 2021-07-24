package com.katoo.cocktail.presentation.result

import com.katoo.cocktail.domain.models.Error
import com.katoo.cocktail.domain.result.Result

sealed class PresentationResult<out Model> {
    class Loading<out Model> : PresentationResult<Model>()
    data class Failure<out Model>(val error: Error) : PresentationResult<Model>()
    data class Success<out Model>(val data: Model) : PresentationResult<Model>()
}

fun <Model> Result<Model>.toPresentationResult(): PresentationResult<Model> {
    return when (this) {
        is Result.Failure -> PresentationResult.Failure(error)
        is Result.Success -> PresentationResult.Success(data)
    }
}