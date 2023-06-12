package dev.brunofelix.marvelapp.feature_character.domain.model

import android.os.Parcelable
import dev.brunofelix.marvelapp.core.domain.model.Thumbnail
import dev.brunofelix.marvelapp.feature_character.data.local.entity.CharacterEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
) : Parcelable {

    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail?.toThumbnailEntity()
        )
    }
}