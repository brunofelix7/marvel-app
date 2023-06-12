package dev.brunofelix.marvelapp.core.domain.model

import android.os.Parcelable
import dev.brunofelix.marvelapp.core.data.local.entity.ThumbnailEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable {

    fun toThumbnailEntity(): ThumbnailEntity {
        return ThumbnailEntity(
            path = path,
            extension = extension
        )
    }
}