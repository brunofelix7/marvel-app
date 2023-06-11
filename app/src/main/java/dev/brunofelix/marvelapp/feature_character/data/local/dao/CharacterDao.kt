package dev.brunofelix.marvelapp.feature_character.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.brunofelix.marvelapp.feature_character.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CharacterEntity): Long

    @Delete
    suspend fun delete(entity: CharacterEntity)

    @Query("SELECT * FROM characters ORDER BY id")
    fun list(): Flow<List<CharacterEntity>>
}