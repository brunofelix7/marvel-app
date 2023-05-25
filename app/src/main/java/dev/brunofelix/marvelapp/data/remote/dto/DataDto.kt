package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName

abstract class DataDto<T: Any>(
    @SerializedName("data")
    var data: ResultsDto<T>? = null
)
