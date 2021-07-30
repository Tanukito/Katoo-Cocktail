package com.katoo.cocktail.presentation.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.katoo.cocktail.presentation.mapper.toStringResId
import com.katoo.cocktail.presentation.result.PresentationResult

fun <Model> PresentationResult<List<Model>>.handleListView(
    context: Context,
    loaderView: View,
    errorView: View,
    emptyView: View,
    recyclerView: RecyclerView
) {
    when (this) {
        is PresentationResult.Loading -> {
            loaderView.isVisible = true
            errorView.isVisible = false
            emptyView.isVisible = false
            recyclerView.isVisible = false
        }
        is PresentationResult.Failure -> {
            loaderView.isVisible = false
            errorView.isVisible = true
            emptyView.isVisible = false
            recyclerView.isVisible = false

            Toast.makeText(context, error.toStringResId(), Toast.LENGTH_SHORT).show()
        }
        is PresentationResult.Success -> {
            loaderView.isVisible = false
            errorView.isVisible = false
            emptyView.isVisible = data.isEmpty()
            recyclerView.isVisible = data.isNotEmpty()

            recyclerView.getBaseAdapter<Model>()?.submitList(data)
        }
    }
}