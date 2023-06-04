package dev.brunofelix.marvelapp.data.remote

import dev.brunofelix.marvelapp.feature_character.data.dto.CharacterDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") name: String? = null
    ) : Response<CharacterDataDto>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId: Int
    ) : Response<CharacterDataDto>
}