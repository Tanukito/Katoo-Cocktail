package com.katoo.cocktail.presentation.screens.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import com.katoo.cocktail.presentation.BaseFragment
import com.katoo.cocktail.presentation.databinding.FragmentIngredientsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientsFragment : BaseFragment<FragmentIngredientsBinding, IngredientsViewModel>() {

    override val viewModel: IngredientsViewModel by viewModel()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIngredientsBinding {
        return FragmentIngredientsBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.ingredients.run {
            setHasFixedSize(true)
            adapter = IngredientsAdapter()
        }
    }

    override fun initViewModel() {

    }
}
