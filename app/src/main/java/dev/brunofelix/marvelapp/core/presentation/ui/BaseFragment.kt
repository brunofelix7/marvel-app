package dev.brunofelix.marvelapp.core.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel> constructor(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    protected abstract val viewModel: VM
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    abstract fun configureUI()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}