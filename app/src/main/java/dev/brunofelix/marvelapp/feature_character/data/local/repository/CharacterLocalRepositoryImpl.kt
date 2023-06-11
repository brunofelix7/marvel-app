package dev.brunofelix.marvelapp.feature_character.data.local.repository

import dev.brunofelix.marvelapp.core.data.local.MarvelDatabase
import dev.brunofelix.marvelapp.feature_character.data.local.entity.CharacterEntity
import dev.brunofelix.marvelapp.feature_character.domain.repository.CharacterLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterLocalRepositoryImpl @Inject constructor(
    private val db: MarvelDatabase
) : CharacterLocalRepository {

    override suspend fun insert(entity: CharacterEntity) = db.characterDao.insert(entity)

    override suspend fun delete(entity: CharacterEntity) = db.characterDao.delete(entity)

    override fun list(): Flow<List<CharacterEntity>> = db.characterDao.list()
}