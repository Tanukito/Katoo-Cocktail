package com.katoo.cocktail.presentation.screens.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.databinding.FragmentDrinksBinding
import com.katoo.cocktail.presentation.databinding.FragmentIngredientsBinding
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinksFragment : BaseFragment<FragmentDrinksBinding, DrinksViewModel>() {

    override val viewModel: DrinksViewModel by viewModel()

//    private val ingredientsAdapter: IngredientsAdapter
//        get() = binding.ingredients.adapter as IngredientsAdapter

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrinksBinding {
        return FragmentDrinksBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
//        binding.ingredients.run {
//            setHasFixedSize(true)
//            adapter = IngredientsAdapter()
//        }
//
//        binding.emptyRetry.setOnClickListener {
//            viewModel.emptyRetryClicked()
//        }
    }

    override fun initViewModel() {
//        viewModel.ingredientsLD.observe(this, { result ->
//            handleIngredients(result)
//        })
    }

//    private fun handleIngredients(result: PresentationResult<List<Ingredient>>) {
//        when (result) {
//            is PresentationResult.Loading -> {
//                binding.ingredients.isVisible = false
//                binding.emptyGroup.isVisible = false
//                binding.loader.isVisible = true
//            }
//            is PresentationResult.Failure -> {
//                binding.ingredients.isVisible = false
//                binding.emptyGroup.isVisible = true
//                binding.loader.isVisible = false
//
//                ingredientsAdapter.submitList(emptyList())
//            }
//            is PresentationResult.Success -> {
//                binding.ingredients.isVisible = result.data.isNotEmpty()
//                binding.emptyGroup.isVisible = result.data.isEmpty()
//                binding.loader.isVisible = false
//
//                ingredientsAdapter.submitList(result.data)
//            }
//        }
//    }
}
