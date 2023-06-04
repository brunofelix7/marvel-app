package dev.brunofelix.marvelapp.data.remote.repository

import dev.brunofelix.marvelapp.data.remote.MarvelApi
import dev.brunofelix.marvelapp.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : MarvelRepository {

    override suspend fun getCharacters(name: String?) = api.getCharacters(name)

    override suspend fun getComics(characterId: Int) = api.getComics(characterId)
}