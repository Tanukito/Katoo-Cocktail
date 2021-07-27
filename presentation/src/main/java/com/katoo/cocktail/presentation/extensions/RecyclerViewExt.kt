package com.katoo.cocktail.presentation.extensions

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
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

            getBaseAdapter<Model>()?.submitList(emptyList())
        }
        is PresentationResult.Success -> {
            isVisible = result.data.isNotEmpty()
            emptyState.isVisible = result.data.isEmpty()
            loader.isVisible = false

            getBaseAdapter<Model>()?.submitList(result.data)
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun <Model> RecyclerView.getBaseAdapter(): BaseAdapter<Model, *, *>? {
    return adapter as? BaseAdapter<Model, *, *>
}