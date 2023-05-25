package dev.brunofelix.marvelapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.domain.Character

data class CharacterDto(
    @SerializedName("id")
    var id: Long?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("thumbnail")
    var thumbnail: ThumbnailDto?
) {
    fun toCharacter(): Character {
        return Character(
            name = name ?: "",
            description = description ?: "",
            thumbnail = thumbnail?.toThumbnail()
        )
    }
}
