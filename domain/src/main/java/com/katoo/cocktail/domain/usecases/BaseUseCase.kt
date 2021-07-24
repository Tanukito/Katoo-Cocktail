package com.katoo.cocktail.domain.usecases

import com.katoo.cocktail.domain.result.Result

abstract class BaseUseCase<in Params, out Model> {

    suspend operator fun invoke(params: Params? = null): Result<Model> {
        return doAction(params)
    }

    protected abstract suspend fun doAction(params: Params?): Result<Model>
}