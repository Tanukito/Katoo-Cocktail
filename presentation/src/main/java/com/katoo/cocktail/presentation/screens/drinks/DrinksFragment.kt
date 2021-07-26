package com.katoo.cocktail.presentation.screens.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.presentation.databinding.FragmentDrinksBinding
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinksFragment : BaseFragment<FragmentDrinksBinding, DrinksViewModel>() {

    private val navArgs by navArgs<DrinksFragmentArgs>()

    override val viewModel: DrinksViewModel by viewModel {
        parametersOf(navArgs.ingredient)
    }

    private val drinksAdapter: DrinksAdapter
        get() = binding.drinks.adapter as DrinksAdapter

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrinksBinding {
        return FragmentDrinksBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.drinks.run {
            setHasFixedSize(true)
            adapter = DrinksAdapter()
        }

        binding.emptyRetry.setOnClickListener {
            viewModel.emptyRetryClicked()
        }
    }

    override fun initViewModel() {
        viewModel.drinksLD.observe(this, { result ->
            handleIngredients(result)
        })
    }

    private fun handleIngredients(result: PresentationResult<List<Drink>>) {
        when (result) {
            is PresentationResult.Loading -> {
                binding.drinks.isVisible = false
                binding.emptyGroup.isVisible = false
                binding.loader.isVisible = true
            }
            is PresentationResult.Failure -> {
                binding.drinks.isVisible = false
                binding.emptyGroup.isVisible = true
                binding.loader.isVisible = false

                drinksAdapter.submitList(emptyList())
            }
            is PresentationResult.Success -> {
                binding.drinks.isVisible = result.data.isNotEmpty()
                binding.emptyGroup.isVisible = result.data.isEmpty()
                binding.loader.isVisible = false

                drinksAdapter.submitList(result.data)
            }
        }
    }
}
