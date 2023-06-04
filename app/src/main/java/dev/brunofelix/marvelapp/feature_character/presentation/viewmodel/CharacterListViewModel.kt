package dev.brunofelix.marvelapp.feature_character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.brunofelix.marvelapp.core.data.DataSourceState
import dev.brunofelix.marvelapp.feature_character.domain.use_case.ListCharactersUseCase
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.presentation.ui.CharacterUIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: ListCharactersUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var _charactersListState = MutableStateFlow(CharacterUIState())
    val charactersListState: StateFlow<CharacterUIState> get() = _charactersListState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() = viewModelScope.launch {
        useCase.invoke().onEach { response ->
            when (response) {
                is DataSourceState.Loading -> {
                    _charactersListState.value = CharacterUIState(isLoading = true)
                }
                is DataSourceState.Success -> {
                    _charactersListState.value = CharacterUIState(data = response.data)
                }
                is DataSourceState.Error -> {
                    _charactersListState.value = CharacterUIState(isLoading = false)
                    _uiEvent.emit(UIEvent.ShowSnackBar(response.message))
                }
            }
        }.launchIn(this)
    }
}