package dev.brunofelix.marvelapp.feature_character.domain.model

import android.os.Parcelable
import dev.brunofelix.marvelapp.core.domain.model.Thumbnail
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail?
) : Parcelable