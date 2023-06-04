package dev.brunofelix.marvelapp.feature_comic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ComicDataDto(
    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var data: ComicContainerDto? = null
)