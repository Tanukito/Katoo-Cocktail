package com.katoo.cocktail.data.mappers

import com.katoo.cocktail.domain.error.Error
import java.net.UnknownHostException

fun Exception.toDomain(): Error {
    return when (this) {
        is UnknownHostException -> Error.UnknownHost
        else -> Error.Unknown
    }
}