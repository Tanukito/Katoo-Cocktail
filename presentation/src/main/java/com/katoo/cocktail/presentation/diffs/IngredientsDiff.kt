package com.katoo.cocktail.presentation.diffs

import androidx.recyclerview.widget.DiffUtil
import com.katoo.cocktail.domain.models.Ingredient

class IngredientsDiff : DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.name == newItem.name
    }
}