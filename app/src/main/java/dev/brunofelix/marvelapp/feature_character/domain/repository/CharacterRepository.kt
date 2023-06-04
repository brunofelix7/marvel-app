package dev.brunofelix.marvelapp.feature_character.domain.repository

import dev.brunofelix.marvelapp.feature_character.data.dto.CharacterDataDto
import retrofit2.Response

interface CharacterRepository {
    suspend fun fetchCharacters(name: String? = null): Response<CharacterDataDto>
}