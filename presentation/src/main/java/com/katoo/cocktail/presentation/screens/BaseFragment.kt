package com.katoo.cocktail.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out Binding : ViewBinding, out ViewModel : BaseViewModel> : Fragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initViewBinding(inflater, container)
        return binding.root
    }

    protected abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    protected abstract fun initViews()
    protected abstract fun initViewModel()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}