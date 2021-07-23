package com.katoo.cocktail.presentation.screens.ingredients


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.databinding.ViewIngredientBinding

class IngredientsAdapter : ListAdapter<Ingredient, IngredientsAdapter.IngredientsViewHolder>(
    IngredientsDiff()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
            ViewIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IngredientsViewHolder(
        private val binding: ViewIngredientBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {
            binding.name.text = ingredient.name
        }
    }

}