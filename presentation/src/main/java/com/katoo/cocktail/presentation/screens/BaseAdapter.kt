package com.katoo.cocktail.presentation.screens

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, B : ViewBinding, VH : BaseViewHolder<T, B>>(
    diff: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diff) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class BaseViewHolder<T, B : ViewBinding>(
    protected val binding: B
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}

