package com.katoo.cocktail.presentation.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.katoo.cocktail.domain.models.Ingredient
import com.katoo.cocktail.presentation.R
import com.katoo.cocktail.presentation.databinding.FragmentSearchBinding
import com.katoo.cocktail.presentation.extensions.hideKeyboard
import com.katoo.cocktail.presentation.extensions.showKeyboard
import com.katoo.cocktail.presentation.result.PresentationResult
import com.katoo.cocktail.presentation.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel()

    private val searchAdapter: SearchAdapter
        get() = binding.ingredients.adapter as SearchAdapter

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

        binding.ingredients.run {
            setHasFixedSize(true)
            adapter = SearchAdapter(viewModel::ingredientClicked)
        }

        binding.emptyState.emptyStateDescription.text =
            String.format(getString(R.string.empty_items), getString(R.string.ingredients))
        binding.emptyState.emptyStateRetry.setOnClickListener {
            viewModel.emptyRetryClicked()
        }
    }

    override fun initViewModel() {
        viewModel.ingredientsLD.observe(this, { result ->
            handleIngredients(result)
        })
    }

    private fun handleIngredients(result: PresentationResult<List<Ingredient>>) {
        when (result) {
            is PresentationResult.Loading -> {
                binding.ingredients.isVisible = false
                binding.emptyState.root.isVisible = false
                binding.loader.isVisible = true
            }
            is PresentationResult.Failure -> {
                binding.ingredients.isVisible = false
                binding.emptyState.root.isVisible = true
                binding.loader.isVisible = false

                searchAdapter.submitList(emptyList())
            }
            is PresentationResult.Success -> {
                binding.ingredients.isVisible = true
                binding.emptyState.root.isVisible = false
                binding.loader.isVisible = false

                searchAdapter.submitList(result.data)
            }
        }
    }
}
