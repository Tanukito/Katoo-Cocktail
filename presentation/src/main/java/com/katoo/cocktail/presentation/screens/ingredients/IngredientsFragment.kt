package com.katoo.cocktail.presentation.screens.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.R
import com.katoo.cocktail.presentation.databinding.FragmentIngredientsBinding
import com.katoo.cocktail.presentation.extensions.handleListView
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
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
        binding.emptyState.emptyStateDescription.text =
            String.format(getString(R.string.empty_items), getString(R.string.ingredients))

        binding.error.errorRetry.setOnClickListener {
            viewModel.emptyRetryClicked()
        }

        binding.ingredients.run {
            setHasFixedSize(true)
            adapter = IngredientsAdapter(viewModel::ingredientClicked)
        }
    }

    override fun initViewModel() {
        viewModel.ingredientsLD.observe(this, { result ->
            handleIngredients(result)
        })
    }

    private fun handleIngredients(result: PresentationResult<List<Ingredient>>) {
        result.handleListView(
            requireContext(),
            binding.loader,
            binding.error.root,
            binding.emptyState.root,
            binding.ingredients
        )
    }
}
