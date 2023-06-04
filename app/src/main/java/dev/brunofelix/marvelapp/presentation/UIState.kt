package dev.brunofelix.marvelapp.presentation

sealed class UIState<T>(val data: T? = null, val message: String = "") {
    class Empty<T>: UIState<T>()
    class Loading<T>: UIState<T>()
    class Success<T>(data: T?): UIState<T>(data)
    class Error<T>(message: String, data: T? = null): UIState<T>(data, message)
}