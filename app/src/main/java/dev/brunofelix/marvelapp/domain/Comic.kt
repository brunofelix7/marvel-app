package dev.brunofelix.marvelapp.domain

data class Comic(
    val title: String,
    val description: String,
    val thumbnail: Thumbnail?
)
