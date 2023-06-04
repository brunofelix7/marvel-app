package dev.brunofelix.marvelapp.feature_comic.data.remote.repository

import dev.brunofelix.marvelapp.core.data.remote.MarvelApi
import dev.brunofelix.marvelapp.feature_comic.domain.repository.ComicRepository
import retrofit2.Response
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : ComicRepository {
    override suspend fun fetchComics(characterId: Int) = api.getComics(characterId)
}