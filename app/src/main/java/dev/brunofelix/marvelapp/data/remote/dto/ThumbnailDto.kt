package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.domain.Thumbnail

data class ThumbnailDto(
    @SerializedName("path")
    var path: String?,

    @SerializedName("extension")
    var extension: String?
) {
    fun toThumbnail(): Thumbnail {
        return Thumbnail(
            path = path ?: "",
            extension = extension ?: ""
        )
    }
}
