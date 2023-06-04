package dev.brunofelix.marvelapp.feature_character.data.repository

import dev.brunofelix.marvelapp.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : CharacterRepository {

    override suspend fun fetchCharacters(name: String?) = api.getCharacters(name)
}