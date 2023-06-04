package dev.brunofelix.marvelapp.domain.repository

import dev.brunofelix.marvelapp.data.remote.dto.CharacterDataDto
import retrofit2.Response

interface MarvelRepository {
    suspend fun getCharacters(name: String? = null): Response<CharacterDataDto>
    suspend fun getComics(characterId: Int): Response<CharacterDataDto>
}