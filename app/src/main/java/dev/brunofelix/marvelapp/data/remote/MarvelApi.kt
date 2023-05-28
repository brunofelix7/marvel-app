package dev.brunofelix.marvelapp.data.remote

import dev.brunofelix.marvelapp.data.remote.dto.CharacterDto
import dev.brunofelix.marvelapp.data.remote.dto.ComicDto
import dev.brunofelix.marvelapp.data.remote.dto.DataDto
import dev.brunofelix.marvelapp.data.remote.dto.ResultsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") name: String? = null
    ) : Response<DataDto<ResultsDto<CharacterDto>>>

    @GET("/characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId: Int
    ) : Response<DataDto<ResultsDto<ComicDto>>>
}
