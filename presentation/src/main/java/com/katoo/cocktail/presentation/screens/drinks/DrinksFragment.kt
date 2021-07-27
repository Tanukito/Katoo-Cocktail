package com.katoo.cocktail.presentation.screens.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.katoo.cocktail.domain.models.Drink
import com.katoo.cocktail.presentation.R
import com.katoo.cocktail.presentation.databinding.FragmentDrinksBinding
import com.katoo.cocktail.presentation.extensions.handleResult
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinksFragment : BaseFragment<FragmentDrinksBinding, DrinksViewModel>() {

    private val navArgs by navArgs<DrinksFragmentArgs>()

    override val viewModel: DrinksViewModel by viewModel {
        parametersOf(navArgs.ingredient)
    }

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

        binding.emptyState.emptyStateDescription.text =
            String.format(getString(R.string.empty_items), getString(R.string.drinks))
        binding.emptyState.emptyStateRetry.setOnClickListener {
            viewModel.emptyRetryClicked()
        }
    }

    override fun initViewModel() {
        viewModel.drinksLD.observe(this, { result ->
            handleIngredients(result)
        })
    }

    private fun handleIngredients(result: PresentationResult<List<Drink>>) {
        binding.drinks.handleResult(
            binding.loader,
            binding.emptyState.root,
            result
        )
    }
}
