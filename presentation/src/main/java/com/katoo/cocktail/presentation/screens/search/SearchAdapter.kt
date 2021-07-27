package com.katoo.cocktail.presentation.screens.search


import android.view.LayoutInflater
import android.view.ViewGroup
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.databinding.ViewSearchBinding
import com.katoo.cocktail.presentation.screens.BaseAdapter
import com.katoo.cocktail.presentation.screens.BaseViewHolder
import com.katoo.cocktail.presentation.screens.diffs.IngredientsDiff
import com.katoo.cocktail.presentation.screens.search.SearchAdapter.SearchViewHolder

class SearchAdapter(
    private val ingredientClicked: (ingredient: Ingredient) -> Unit
) : BaseAdapter<Ingredient, ViewSearchBinding, SearchViewHolder>(
    IngredientsDiff()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ViewSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class SearchViewHolder(
        binding: ViewSearchBinding
    ) : BaseViewHolder<Ingredient, ViewSearchBinding>(binding) {

        override fun bind(item: Ingredient) {
            binding.root.text = item.name
            binding.root.setOnClickListener {
                ingredientClicked(item)
            }
        }
    }
}