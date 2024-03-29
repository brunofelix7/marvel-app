package dev.brunofelix.marvelapp.feature_character.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterContainerDto(
    @SerializedName("results")
    var results: List<CharacterDto>? = emptyList()
)