package dev.brunofelix.marvelapp.feature_character.domain.use_case

import dev.brunofelix.marvelapp.BuildConfig
import dev.brunofelix.marvelapp.core.data.DataSourceState
import dev.brunofelix.marvelapp.feature_character.domain.model.Character
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterLocalRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ListLocalCharactersUseCase @Inject constructor(
    private val repository: CharacterLocalRepository
) {

    suspend operator fun invoke() = flow<DataSourceState<List<Character>>> {
        try {
            emit(DataSourceState.Loading())

            repository.list().collectLatest { result ->
                if (result.isNullOrEmpty()) {
                    emit(DataSourceState.Error(message = BuildConfig.EMPTY_LIST))
                } else {
                    emit(DataSourceState.Success(data = result.map { it.toCharacter() }))
                }
            }
        } catch (e: Exception) {
            Timber.e("$e")
            emit(DataSourceState.Error(message = e.message ?: BuildConfig.UNKNOWN_ERROR))
        }
    }
}