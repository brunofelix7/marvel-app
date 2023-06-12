package dev.brunofelix.marvelapp.feature_character.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.core.data.DataSourceState
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.domain.model.Character
import dev.brunofelix.marvelapp.feature_character.domain.use_case.DeleteCharacterUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.ListLocalCharactersUseCase
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
class CharacterFavoriteViewModel @Inject constructor(
    private val listLocalCharactersUseCase: ListLocalCharactersUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
) : ViewModel() {

    @Inject
    lateinit var resources: Resources

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var _favoriteState = MutableStateFlow(CharacterUIState())
    val favoriteState: StateFlow<CharacterUIState> get() = _favoriteState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() = viewModelScope.launch {
        listLocalCharactersUseCase.invoke().onEach { result ->
            when (result) {
                is DataSourceState.Loading -> {
                    _favoriteState.value = CharacterUIState(isLoading = true)
                }
                is DataSourceState.Success -> {
                    _favoriteState.value = CharacterUIState(data = result.data)
                }
                is DataSourceState.Error -> {
                    _favoriteState.value = CharacterUIState(isLoading = false)
                    _uiEvent.emit(UIEvent.ShowSnackBar(result.message))
                }
            }
        }.launchIn(this)
    }

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        deleteCharacterUseCase.invoke(character)
        _uiEvent.emit(UIEvent.ShowSnackBar(resources.getString(R.string.message_delete_character)))
    }
}