package dev.brunofelix.marvelapp.core.data

sealed class DataSourceState<T>(val data: T? = null, val message: String = "") {
    class Loading<T>: DataSourceState<T>()
    class Success<T>(data: T?): DataSourceState<T>(data)
    class Error<T>(message: String, data: T? = null): DataSourceState<T>(data, message)
}