package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDataDto(
    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var data: CharacterContainer? = null
)