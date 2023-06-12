package dev.brunofelix.marvelapp.feature_character.domain.repository

import dev.brunofelix.marvelapp.feature_character.data.remote.dto.CharacterDataDto
import dev.brunofelix.marvelapp.feature_character.data.remote.dto.ComicDataDto
import retrofit2.Response

interface CharacterRemoteRepository {
    suspend fun fetchCharacters(name: String? = null): Response<CharacterDataDto>
    suspend fun findComics(characterId: Int): Response<ComicDataDto>
}