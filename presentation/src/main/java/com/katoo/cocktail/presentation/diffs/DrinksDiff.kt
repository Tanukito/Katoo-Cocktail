package com.katoo.cocktail.presentation.diffs

import androidx.recyclerview.widget.DiffUtil
import com.katoo.cocktail.domain.models.Drink

class DrinksDiff : DiffUtil.ItemCallback<Drink>() {
    override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
        return oldItem.id == newItem.id
    }
}