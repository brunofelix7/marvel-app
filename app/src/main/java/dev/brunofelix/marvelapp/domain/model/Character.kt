package dev.brunofelix.marvelapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
) : Parcelable
