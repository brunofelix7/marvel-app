package dev.brunofelix.marvelapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.brunofelix.marvelapp.data.ResourceState
import dev.brunofelix.marvelapp.domain.model.Character
import dev.brunofelix.marvelapp.domain.use_case.ListCharactersUseCase
import dev.brunofelix.marvelapp.presentation.UIEvent
import dev.brunofelix.marvelapp.presentation.UIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCase: ListCharactersUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var _charactersList = MutableStateFlow<UIState<List<Character>>>(UIState.Empty())
    val charactersList: StateFlow<UIState<List<Character>>> get() = _charactersList

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(name: String? = null) = viewModelScope.launch {
        useCase.invoke(name).onEach { response ->
            when (response) {
                is ResourceState.Loading -> {
                    _charactersList.value = UIState.Loading()
                    _uiEvent.emit(UIEvent.ShowProgressBar(isLoading = true))
                }
                is ResourceState.Success -> {
                    _charactersList.value = UIState.Success(response.data)
                    _uiEvent.emit(UIEvent.ShowProgressBar(isLoading = false))
                }
                is ResourceState.Error -> {
                    _charactersList.value = UIState.Error(response.message)
                    _uiEvent.emit(UIEvent.ShowProgressBar(isLoading = false))
                    _uiEvent.emit(UIEvent.ShowSnackBar(response.message))
                }
            }
        }.launchIn(this)
    }
}