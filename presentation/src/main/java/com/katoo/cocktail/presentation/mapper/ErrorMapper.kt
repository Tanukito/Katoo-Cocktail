package com.katoo.cocktail.presentation.mapper

import com.katoo.cocktail.domain.error.Error
import com.katoo.cocktail.presentation.R

fun Error.toStringResId(): Int {
    return when (this) {
        Error.NoConnection -> R.string.no_connection_error
        Error.UnknownHost -> R.string.unknown_host_error
        Error.EmptyBody -> R.string.empty_body_error
        Error.Unknown -> R.string.unknown_error
    }
}