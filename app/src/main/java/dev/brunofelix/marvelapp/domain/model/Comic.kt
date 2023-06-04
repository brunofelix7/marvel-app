package dev.brunofelix.marvelapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail?
) : Parcelable
