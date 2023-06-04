package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.databinding.FragmentCharacterSearchBinding
import dev.brunofelix.marvelapp.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterViewModel

@AndroidEntryPoint
class CharacterSearchFragment : BaseFragment<FragmentCharacterSearchBinding, CharacterViewModel>(
    FragmentCharacterSearchBinding::inflate
) {

    override val viewModel: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
    }

    override fun configureUI() {

    }
}