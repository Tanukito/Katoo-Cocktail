package com.katoo.cocktail.presentation.extensions

import androidx.recyclerview.widget.RecyclerView
import com.katoo.cocktail.presentation.screens.BaseAdapter

@Suppress("UNCHECKED_CAST")
fun <Model> RecyclerView.getBaseAdapter(): BaseAdapter<Model, *, *>? {
    return adapter as? BaseAdapter<Model, *, *>
}