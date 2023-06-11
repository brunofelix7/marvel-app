package dev.brunofelix.marvelapp.feature_character.domain.repository

import dev.brunofelix.marvelapp.feature_character.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterLocalRepository {
    suspend fun insert(entity: CharacterEntity): Long
    suspend fun delete(entity: CharacterEntity)
    fun list(): Flow<List<CharacterEntity>>
}