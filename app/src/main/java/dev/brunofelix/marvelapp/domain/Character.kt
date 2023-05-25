package dev.brunofelix.marvelapp.domain

data class Character(
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
)
