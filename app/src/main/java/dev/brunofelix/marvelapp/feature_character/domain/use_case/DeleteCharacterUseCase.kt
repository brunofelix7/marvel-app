package dev.brunofelix.marvelapp.feature_character.domain.use_case

import dev.brunofelix.marvelapp.feature_character.domain.model.Character
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterLocalRepository
import javax.inject.Inject

class DeleteCharacterUseCase @Inject constructor(
    private val repository: CharacterLocalRepository
) {

    suspend operator fun invoke(character: Character) {
        repository.delete(character.toCharacterEntity())
    }
}