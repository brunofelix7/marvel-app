package dev.brunofelix.marvelapp.feature_character.presentation.ui

import dev.brunofelix.marvelapp.feature_character.domain.model.Comic

data class ComicUIState(
    val isLoading: Boolean = false,
    val data: List<Comic>? = emptyList()
)