package dev.brunofelix.marvelapp.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.core.domain.model.Thumbnail

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
