package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName

abstract class ResultsDto<T: Any>  {
    @SerializedName("results")
    var results: List<T>? = emptyList()
}
