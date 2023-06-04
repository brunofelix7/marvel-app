package dev.brunofelix.marvelapp.core.presentation.ui

sealed class UIEvent {
    data class ShowToast(val message: String): UIEvent()
    data class ShowSnackBar(val message: String): UIEvent()
}