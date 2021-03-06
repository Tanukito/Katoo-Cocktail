package com.katoo.cocktail.presentation.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.R
import com.katoo.cocktail.presentation.databinding.FragmentSearchBinding
import com.katoo.cocktail.presentation.extensions.handleListView
import com.katoo.cocktail.presentation.extensions.hideKeyboard
import com.katoo.cocktail.presentation.extensions.showKeyboard
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.searchBar.addTextChangedListener { editable ->
            editable?.run {
                viewModel.searchChanged(toString())
            }
        }
        binding.searchBar.setOnEditorActionListener { textView, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    textView.hideKeyboard()
                    true
                }
                else -> false
            }
        }
        binding.searchBar.showKeyboard()

        binding.emptyState.emptyStateDescription.text =
            String.format(getString(R.string.empty_items), getString(R.string.ingredients))

        binding.error.errorRetry.setOnClickListener {
            viewModel.emptyRetryClicked()
        }

        binding.ingredients.run {
            setHasFixedSize(true)
            adapter = SearchAdapter(viewModel::ingredientClicked)
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
