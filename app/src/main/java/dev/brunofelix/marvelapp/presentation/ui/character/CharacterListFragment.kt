package dev.brunofelix.marvelapp.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.databinding.FragmentCharacterListBinding
import dev.brunofelix.marvelapp.extension.showSnackBar
import dev.brunofelix.marvelapp.extension.showToast
import dev.brunofelix.marvelapp.presentation.UIEvent
import dev.brunofelix.marvelapp.presentation.UIState
import dev.brunofelix.marvelapp.presentation.adapter.CharacterAdapter
import dev.brunofelix.marvelapp.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.presentation.viewmodel.CharacterViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterViewModel>(
    FragmentCharacterListBinding::inflate
) {

    override val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
        collectData()
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    override fun configureUI() {
        configureAdapter()
        configureUIEvents()
    }

    private fun configureRecyclerView() = with(binding) {
        rvCharacters.apply {
            isVisible = true
            adapter = characterAdapter
        }
    }

    private fun configureAdapter() {
        characterAdapter.setOnClickListener { character ->
            val action = CharacterListFragmentDirections.navFromCharacterToCharacterDetails(character)
            findNavController().navigate(action)
        }
    }

    private fun collectData() {
        job = lifecycleScope.launch {
            viewModel.charactersList.collect { uiState ->
                when (uiState) {
                    is UIState.Loading -> {
                        binding.rvCharacters.isVisible = false
                    }
                    is UIState.Success -> {
                        uiState.data?.let { data ->
                            characterAdapter.differ.submitList(data)
                            configureRecyclerView()
                        }
                    }
                    else -> { }
                }
            }
        }
    }

    private fun configureUIEvents() {
        lifecycleScope.launch {
            viewModel.uiEvent.collect { uiEvent ->
                when (uiEvent) {
                    is UIEvent.ShowProgressBar -> {
                        binding.progressBar.isVisible = uiEvent.isLoading
                    }
                    is UIEvent.ShowToast -> {
                        view?.showToast(uiEvent.message)
                    }
                    is UIEvent.ShowSnackBar -> {
                        view?.showSnackBar(uiEvent.message)
                    }
                }
            }
        }
    }
}