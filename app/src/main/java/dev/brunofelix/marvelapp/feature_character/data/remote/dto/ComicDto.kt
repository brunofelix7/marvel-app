package dev.brunofelix.marvelapp.feature_character.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.core.data.remote.dto.ThumbnailDto
import dev.brunofelix.marvelapp.feature_character.domain.model.Comic

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
            id = id ?: 0,
            title = title ?: "",
            description = description ?: "",
            thumbnail = thumbnail?.toThumbnail()
        )
    }
}
