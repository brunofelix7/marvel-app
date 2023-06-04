package dev.brunofelix.marvelapp.feature_comic.domain.repository

import dev.brunofelix.marvelapp.feature_comic.data.remote.dto.ComicDataDto
import retrofit2.Response

interface ComicRepository {
    suspend fun fetchComics(characterId: Int): Response<ComicDataDto>
}