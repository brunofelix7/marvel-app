package dev.brunofelix.marvelapp.feature_character.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brunofelix.marvelapp.core.data.local.entity.ThumbnailEntity
import dev.brunofelix.marvelapp.feature_character.domain.model.Character

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailEntity?
) {
    fun toCharacter(): Character {
        return Character(
            id = id,
            name = name ?: "",
            description = description ?: "",
            thumbnail = thumbnail?.toThumbnail()
        )
    }
}