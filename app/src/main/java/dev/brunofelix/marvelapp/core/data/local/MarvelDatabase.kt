package dev.brunofelix.marvelapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.brunofelix.marvelapp.core.data.local.converter.Converters
import dev.brunofelix.marvelapp.feature_character.data.local.dao.CharacterDao
import dev.brunofelix.marvelapp.feature_character.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
}