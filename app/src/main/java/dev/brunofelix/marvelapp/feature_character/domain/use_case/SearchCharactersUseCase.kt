package dev.brunofelix.marvelapp.feature_character.domain.use_case

import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.core.data.DataSourceState
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(name: String) = flow {
        try {
            emit(DataSourceState.Loading())

            val response = repository.fetchCharacters(name)
            val body = response.body()

            if (response.isSuccessful) {
                if (body != null) {
                    emit(DataSourceState.Success(data = body.data?.results?.map { it.toCharacter() }))
                } else {
                    emit(DataSourceState.Error(message = BuildConfig.SERVER_ERROR))
                }
            } else {
                emit(DataSourceState.Error(message = response.message()))
            }
        } catch (t: Throwable) {
            Timber.e("$t")
            when (t) {
                is UnknownHostException -> {
                    emit(DataSourceState.Error(message = BuildConfig.HOST_ERROR))
                }
                is IOException -> {
                    emit(DataSourceState.Error(message = BuildConfig.SERVER_ERROR))
                }
                else -> {
                    emit(DataSourceState.Error(message = BuildConfig.UNKNOWN_ERROR))
                }
            }
        }
    }
}