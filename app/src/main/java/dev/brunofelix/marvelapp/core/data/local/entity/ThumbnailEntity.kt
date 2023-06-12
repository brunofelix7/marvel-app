package dev.brunofelix.marvelapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brunofelix.marvelapp.core.domain.model.Thumbnail

@Entity(tableName = "thumbnails")
data class ThumbnailEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val path: String?,
    val extension: String?
) {
    fun toThumbnail(): Thumbnail {
        return Thumbnail(
            path = path ?: "",
            extension = extension ?: ""
        )
    }
}