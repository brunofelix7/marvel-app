package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.domain.model.Character

data class CharacterContainer(
    @SerializedName("results")
    var results: List<CharacterDto>? = emptyList()
)