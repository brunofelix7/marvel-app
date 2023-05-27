package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.domain.model.Comic

data class ComicDto(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("thumbnail")
    var thumbnail: ThumbnailDto?
) {
    fun toComic(): Comic {
        return Comic(
            title = title ?: "",
            description = description ?: "",
            thumbnail = thumbnail?.toThumbnail()
        )
    }
}
