package dev.brunofelix.marvelapp.feature_comic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ComicContainerDto(
    @SerializedName("results")
    var results: List<ComicDto>? = emptyList()
)