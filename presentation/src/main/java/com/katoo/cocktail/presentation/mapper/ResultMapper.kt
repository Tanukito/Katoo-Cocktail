package com.katoo.cocktail.presentation.mapper

import com.katoo.cocktail.domain.result.Result
import com.katoo.cocktail.presentation.result.PresentationResult

fun <Model> Result<Model>.toPresentation(): PresentationResult<Model> {
    return when (this) {
        is Result.Failure -> PresentationResult.Failure(error)
        is Result.Success -> PresentationResult.Success(data)
    }
}