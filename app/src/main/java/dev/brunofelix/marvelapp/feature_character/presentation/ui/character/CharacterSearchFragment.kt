package dev.brunofelix.marvelapp.feature_character.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.brunofelix.marvelapp.core.extension.hideKeyboard
import dev.brunofelix.marvelapp.databinding.FragmentCharacterSearchBinding
import dev.brunofelix.marvelapp.core.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.feature_character.presentation.viewmodel.CharacterListViewModel

@AndroidEntryPoint
class CharacterSearchFragment : BaseFragment<FragmentCharacterSearchBinding, CharacterListViewModel>(
    FragmentCharacterSearchBinding::inflate
) {

    override val viewModel: CharacterListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
    }

    override fun configureUI() {
        binding.root.setOnClickListener {
            it.hideKeyboard()
        }
    }
}
