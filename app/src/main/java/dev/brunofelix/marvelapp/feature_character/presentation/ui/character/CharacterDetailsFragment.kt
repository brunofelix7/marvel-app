package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.core.extension.hide
import dev.brunofelix.marvelapp.core.extension.loadImage
import dev.brunofelix.marvelapp.core.extension.showSnackBar
import dev.brunofelix.marvelapp.core.extension.showToast
import dev.brunofelix.marvelapp.databinding.FragmentCharacterDetailsBinding
import dev.brunofelix.marvelapp.core.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.domain.model.Character
import dev.brunofelix.marvelapp.feature_character.presentation.adapter.ComicAdapter
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterDetailsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailsViewModel>(
    FragmentCharacterDetailsBinding::inflate
) {

    override val viewModel: CharacterDetailsViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val comicAdapter by lazy { ComicAdapter() }
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
        collectData()
        setData(args.character)
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    override fun configureUI() {
        configureUIEvents()
        configureToolbar()
    }

    private fun configureToolbar() = with(binding) {
        characterDetailsToolbar.inflateMenu(R.menu.menu_details)
        characterDetailsToolbar.title = args.character.name
        characterDetailsToolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        characterDetailsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        characterDetailsToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_favorite -> {
                    viewModel.saveCharacter(args.character)
                    true
                }
                else -> false
            }
        }
    }

    private fun configureRecyclerView() = with(binding) {
        characterDetailsComicsList.apply {
            isVisible = true
            adapter = comicAdapter
        }
    }

    private fun collectData() {
        viewModel.fetchComics(args.character.id)

        job = lifecycleScope.launch {
            viewModel.characterDetailsState.collect { uiState ->
                binding.characterDetailsProgressBar.isVisible = uiState.isLoading

                uiState.data?.let { data ->
                    Timber.d(data.toString())
                    if (data.isNotEmpty() && !uiState.isLoading) {
                        comicAdapter.differ.submitList(data)
                        configureRecyclerView()
                    } else {
                        binding.characterDetailsComicsList.hide()
                    }
                }
            }
        }
    }

    private fun setData(character: Character) = with(binding) {
        characterDetailsName.text = character.name

        if (character.description.isEmpty()) {
            characterDetailsDescription.text = resources.getString(R.string.empty_description)
        } else {
            characterDetailsDescription.text = character.description
        }

        val imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}"
        characterDetailsImage.loadImage(imageUrl)
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
