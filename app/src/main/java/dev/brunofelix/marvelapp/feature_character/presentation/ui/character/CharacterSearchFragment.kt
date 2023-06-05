package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.core.extension.hide
import dev.brunofelix.marvelapp.core.extension.hideKeyboard
import dev.brunofelix.marvelapp.core.extension.showSnackBar
import dev.brunofelix.marvelapp.core.extension.showToast
import dev.brunofelix.marvelapp.databinding.FragmentCharacterSearchBinding
import dev.brunofelix.marvelapp.core.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.presentation.adapter.CharacterAdapter
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterSearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CharacterSearchFragment : BaseFragment<FragmentCharacterSearchBinding, CharacterSearchViewModel>(
    FragmentCharacterSearchBinding::inflate
) {

    override val viewModel: CharacterSearchViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }
    private var job: Job? = null
    private val lastQuery: String = "last-query-key"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
        collectData()

        val query = savedInstanceState?.getString(lastQuery) ?: ""
        configureSearch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(lastQuery, binding.edSearchCharacter.text?.trim().toString())
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    override fun configureUI() {
        binding.root.setOnClickListener {
            it.hideKeyboard()
            binding.edSearchCharacter.clearFocus()
        }
        configureAdapter()
        configureUIEvents()
    }

    private fun configureSearch(query: String) = with(binding) {
        edSearchCharacter.setText(query)
        edSearchCharacter.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateCharacterList()
                true
            } else {
                false
            }
        }
        edSearchCharacter.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                view?.hideKeyboard()
                updateCharacterList()
                true
            } else {
                false
            }
        }
    }

    private fun updateCharacterList() = with(binding) {
        edSearchCharacter.editableText.trim().let {
            if (it.isNotEmpty()) {
                searchQuery(it.toString())
            }
        }
    }

    private fun searchQuery(query: String) {
        viewModel.searchCharacters(query)
    }

    private fun configureRecyclerView() = with(binding) {
        rvSearchCharacter.apply {
            isVisible = true
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun configureAdapter() {
        characterAdapter.setOnClickListener { character ->
            val action = CharacterSearchFragmentDirections.navFromCharacterSearchToCharacterDetails(character)
            findNavController().navigate(action)
        }
    }

    private fun collectData() {
        job = lifecycleScope.launch {
            viewModel.charactersSearchState.collect { uiState ->
                binding.progressBar.isVisible = uiState.isLoading
                binding.rvSearchCharacter.isVisible = !uiState.isLoading

                uiState.data?.let { data ->
                    if (data.isNotEmpty() && !uiState.isLoading) {
                        characterAdapter.differ.submitList(data)
                        configureRecyclerView()
                    } else {
                        binding.rvSearchCharacter.hide()
                        // TODO: Empty layout
                    }
                }
            }
        }
    }

    private fun configureUIEvents() {
        lifecycleScope.launch {
            viewModel.uiEvent.collect { uiEvent ->
                when (uiEvent) {
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