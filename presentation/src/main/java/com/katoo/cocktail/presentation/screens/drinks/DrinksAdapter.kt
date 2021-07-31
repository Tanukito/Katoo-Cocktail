package com.katoo.cocktail.presentation.screens.drinks


import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.presentation.databinding.ViewDrinkBinding
import com.katoo.cocktail.presentation.screens.BaseAdapter
import com.katoo.cocktail.presentation.screens.BaseViewHolder
import com.katoo.cocktail.presentation.diffs.DrinksDiff
import com.katoo.cocktail.presentation.screens.drinks.DrinksAdapter.DrinksViewHolder

class DrinksAdapter : BaseAdapter<Drink, ViewDrinkBinding, DrinksViewHolder>(
    DrinksDiff()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        return DrinksViewHolder(
            ViewDrinkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class DrinksViewHolder(
        binding: ViewDrinkBinding
    ) : BaseViewHolder<Drink, ViewDrinkBinding>(binding) {

        override fun bind(item: Drink) {
            binding.name.text = item.description
            binding.image.load(item.imagePath)
        }
    }

}