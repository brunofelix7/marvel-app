package dev.brunofelix.marvelapp.data

sealed class ResourceState<T>(val data: T? = null, val message: String = "") {
    class Loading<T>: ResourceState<T>()
    class Success<T>(data: T?): ResourceState<T>(data)
    class Error<T>(message: String, data: T? = null): ResourceState<T>(data, message)
}