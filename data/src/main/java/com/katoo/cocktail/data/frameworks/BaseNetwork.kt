package com.katoo.cocktail.data.frameworks

import com.katoo.cocktail.data.frameworks.cocktail.CocktailResponse
import com.katoo.cocktail.domain.models.Error
import com.katoo.cocktail.domain.result.Result
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseNetwork {

    suspend fun <ResponseType, Model> doCall(
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
            Result.Failure(
                if (e is UnknownHostException) {
                    Error.UnknownHost
                } else {
                    Error.Unknown
                }
            )
        }
    }
}