package dev.brunofelix.marvelapp.presentation.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dev.brunofelix.marvelapp.databinding.FragmentCharacterDetailsBinding
import dev.brunofelix.marvelapp.presentation.ui.BaseFragment
import dev.brunofelix.marvelapp.presentation.viewmodel.CharacterViewModel

class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding, CharacterViewModel>(
    FragmentCharacterDetailsBinding::inflate
) {

    override val viewModel: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUI()
    }

    override fun configureUI() {

    }
}
