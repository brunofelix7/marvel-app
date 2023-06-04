package dev.brunofelix.marvelapp.feature_character.domain.use_case

import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.data.ResourceState
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class ListCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(name: String?) = flow {
        try {
            emit(ResourceState.Loading())

            val response = repository.fetchCharacters(name)
            val body = response.body()

            if (response.isSuccessful) {
                if (body != null) {
                    emit(ResourceState.Success(data = body.data?.results?.map { it.toCharacter() }))
                } else {
                    emit(ResourceState.Success(data = emptyList()))
                }
            } else {
                emit(ResourceState.Error(message = response.message()))
            }
        } catch (t: Throwable) {
            Timber.e("$t")
            when (t) {
                is UnknownHostException -> {
                    emit(ResourceState.Error(message = BuildConfig.HOST_ERROR))
                }
                is IOException -> {
                    emit(ResourceState.Error(message = BuildConfig.SERVER_ERROR))
                }
                else -> {
                    emit(ResourceState.Error(message = BuildConfig.UNKNOWN_ERROR))
                }
            }
        }
    }
}