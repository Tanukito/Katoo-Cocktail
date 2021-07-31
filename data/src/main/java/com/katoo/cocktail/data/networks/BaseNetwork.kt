package com.katoo.cocktail.data.networks

import com.katoo.cocktail.data.networks.cocktail.CocktailResponse
import com.katoo.cocktail.data.handlers.ConnectivityHandler
import com.katoo.cocktail.data.mappers.toDomain
import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.domain.result.Result
import retrofit2.Response

abstract class BaseNetwork(
    private val connectivity: ConnectivityHandler
) {

    suspend fun <ResponseType, Model> doCall(
        request: suspend () -> Response<CocktailResponse<ResponseType>>,
        map: (List<ResponseType>) -> Model
    ): Result<Model> {
        return if (!connectivity.isConnected()) {
            Result.Failure(Error.NoConnection)
        } else {
            handleCall(request, map)
        }
    }

    private suspend fun <ResponseType, Model> handleCall(
        request: suspend () -> Response<CocktailResponse<ResponseType>>,
        map: (List<ResponseType>) -> Model
    ): Result<Model> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                body?.run {
                    Result.Success(map(body.drinks))
                } ?: Result.Failure(Error.EmptyBody)
            } else {
                Result.Failure(Error.Unknown)
            }
        } catch (e: Exception) {
            Result.Failure(e.toDomain())
        }
    }
}