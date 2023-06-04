package dev.brunofelix.marvelapp.feature_character.domain.model

import android.os.Parcelable
import dev.brunofelix.marvelapp.domain.model.Thumbnail
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
) : Parcelable
