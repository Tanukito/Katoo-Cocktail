package com.katoo.cocktail.presentation.screens.ingredients


import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.databinding.ViewIngredientBinding
import com.katoo.cocktail.presentation.screens.BaseAdapter
import com.katoo.cocktail.presentation.screens.BaseViewHolder
import com.katoo.cocktail.presentation.screens.diffs.IngredientsDiff
import com.katoo.cocktail.presentation.screens.ingredients.IngredientsAdapter.IngredientsViewHolder

class IngredientsAdapter(
    private val ingredientClicked: (ingredient: Ingredient) -> Unit
) : BaseAdapter<Ingredient, ViewIngredientBinding, IngredientsViewHolder>(
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

    inner class IngredientsViewHolder(
        binding: ViewIngredientBinding
    ) : BaseViewHolder<Ingredient, ViewIngredientBinding>(binding) {

        override fun bind(item: Ingredient) {
            binding.name.text = item.name
            binding.image.load(item.imagePath)
            binding.root.setOnClickListener {
                ingredientClicked(item)
            }
        }
    }
}