package dev.brunofelix.marvelapp.feature_character.presentation.ui

import dev.brunofelix.marvelapp.feature_character.domain.model.Character

data class CharacterUIState(
    val isLoading: Boolean = false,
    val data: List<Character>? = emptyList()
)