package dev.brunofelix.marvelapp.feature_character.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.brunofelix.marvelapp.R
import dev.brunofelix.marvelapp.core.data.DataSourceState
import dev.brunofelix.marvelapp.core.presentation.ui.UIEvent
import dev.brunofelix.marvelapp.feature_character.domain.model.Character
import dev.brunofelix.marvelapp.feature_character.domain.use_case.FindComicsUseCase
import dev.brunofelix.marvelapp.feature_character.domain.use_case.SaveCharacterUseCase
import dev.brunofelix.marvelapp.feature_character.presentation.ui.ComicUIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val findComicsUseCase: FindComicsUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase,
) : ViewModel() {

    @Inject
    lateinit var resources: Resources

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var _characterDetailsState = MutableStateFlow(ComicUIState())
    val characterDetailsState: StateFlow<ComicUIState> get() = _characterDetailsState

    fun fetchComics(characterId: Int) = viewModelScope.launch {
        findComicsUseCase.invoke(characterId).onEach { response ->
            when (response) {
                is DataSourceState.Loading -> {
                    _characterDetailsState.value = ComicUIState(isLoading = true)
                }
                is DataSourceState.Success -> {
                    _characterDetailsState.value = ComicUIState(data = response.data)
                }
                is DataSourceState.Error -> {
                    _characterDetailsState.value = ComicUIState(isLoading = false)
                    _uiEvent.emit(UIEvent.ShowSnackBar(response.message))
                }
            }
        }.launchIn(this)
    }

    fun saveCharacter(character: Character) = viewModelScope.launch {
        val result = saveCharacterUseCase.invoke(character)

        if (result > 0) {
            _uiEvent.emit(UIEvent.ShowSnackBar(resources.getString(R.string.saved_successfully)))
        } else {
            _uiEvent.emit(UIEvent.ShowSnackBar(resources.getString(R.string.an_error_occurred)))
        }
    }
}