package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.core.extension.hide
import dev.brunofelix.marvelapp.core.extension.show
import dev.brunofelix.marvelapp.core.extension.showSnackBar
import dev.brunofelix.marvelapp.core.extension.showToast
import dev.brunofelix.marvelapp.databinding.FragmentCharacterFavoriteBinding
import dev.brunofelix.marvelapp.core.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.presentation.adapter.CharacterAdapter
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterFavoriteViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFavoriteFragment : BaseFragment<FragmentCharacterFavoriteBinding, CharacterFavoriteViewModel>(
    FragmentCharacterFavoriteBinding::inflate
) {

    override val viewModel: CharacterFavoriteViewModel by viewModels()
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
        rvFavoriteCharacter.apply {
            isVisible = true
            adapter = characterAdapter
        }
        ItemTouchHelper(itemTouchHelperCallback()).attachToRecyclerView(rvFavoriteCharacter)
    }

    private fun configureAdapter() {
        characterAdapter.setOnClickListener { character ->
            val action = CharacterFavoriteFragmentDirections.navFromCharacterFavoriteToCharacterDetails(character)
            findNavController().navigate(action)
        }
    }

    private fun itemTouchHelperCallback(): ItemTouchHelper.SimpleCallback{
        return object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val currentItem = characterAdapter.getCurrentItem(viewHolder.bindingAdapterPosition)
                viewModel.deleteCharacter(currentItem)
            }
        }
    }

    private fun collectData() {
        job = lifecycleScope.launch {
            viewModel.favoriteState.collect { uiState ->
                binding.progressBar.isVisible = uiState.isLoading

                uiState.data?.let { data ->
                    if (data.isNotEmpty() && !uiState.isLoading) {
                        binding.tvEmptyList.hide()
                        characterAdapter.differ.submitList(data)
                        configureRecyclerView()
                    } else {
                        if (!uiState.isLoading) {
                            binding.tvEmptyList.show()
                        }
                        binding.rvFavoriteCharacter.hide()
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
