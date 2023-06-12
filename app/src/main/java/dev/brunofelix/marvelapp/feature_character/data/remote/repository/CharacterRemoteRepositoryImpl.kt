package dev.brunofelix.marvelapp.feature_character.data.remote.repository

import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRemoteRepository
import javax.inject.Inject

class CharacterRemoteRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : CharacterRemoteRepository {
    override suspend fun fetchCharacters(name: String?) = api.getCharacters(name)
    override suspend fun findComics(characterId: Int) = api.getComics(characterId)
}