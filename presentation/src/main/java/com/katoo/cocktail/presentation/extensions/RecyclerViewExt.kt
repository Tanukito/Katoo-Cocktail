package com.katoo.cocktail.presentation.extensions

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.katoo.cocktail.presentation.mapper.toStringResId
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseAdapter

fun <Model> RecyclerView.handleResult(
    loader: View,
    emptyState: View,
    result: PresentationResult<List<Model>>
) {
    when (result) {
        is PresentationResult.Loading -> {
            isVisible = false
            emptyState.isVisible = false
            loader.isVisible = true
        }
        is PresentationResult.Failure -> {
            isVisible = false
            emptyState.isVisible = true
            loader.isVisible = false

            Toast.makeText(context, result.error.toStringResId(), Toast.LENGTH_SHORT).show()
        }
        is PresentationResult.Success -> {
            isVisible = true
            emptyState.isVisible = false
            loader.isVisible = false

            getBaseAdapter<Model>()?.submitList(result.data)
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun <Model> RecyclerView.getBaseAdapter(): BaseAdapter<Model, *, *>? {
    return adapter as? BaseAdapter<Model, *, *>
}