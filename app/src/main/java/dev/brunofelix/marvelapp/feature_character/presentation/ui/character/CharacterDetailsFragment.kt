package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.databinding.FragmentCharacterDetailsBinding
import dev.brunofelix.marvelapp.core.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterListViewModel

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding, CharacterListViewModel>(
    FragmentCharacterDetailsBinding::inflate
) {

    override val viewModel: CharacterListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
    }

    override fun configureUI() {

    }
}
