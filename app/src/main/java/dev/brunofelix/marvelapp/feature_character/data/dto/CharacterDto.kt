package dev.brunofelix.marvelapp.feature_character.data.dto

import com.google.gson.annotations.SerializedName
import dev.brunofelix.marvelapp.data.remote.dto.ThumbnailDto
import dev.brunofelix.marvelapp.feature_character.domain.model.Character

data class CharacterDto(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("thumbnail")
    var thumbnail: ThumbnailDto?
) {
    fun toCharacter(): Character {
        return Character(
            id = id ?: 0,
            name = name ?: "",
            description = description ?: "",
            thumbnail = thumbnail?.toThumbnail()
        )
    }
}