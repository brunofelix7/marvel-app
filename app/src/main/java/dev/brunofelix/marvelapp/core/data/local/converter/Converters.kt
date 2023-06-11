package dev.brunofelix.marvelapp.core.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.brunofelix.marvelapp.core.data.local.entity.ThumbnailEntity

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromThumbnail(entity: ThumbnailEntity) : String {
        return Gson().toJson(entity)
    }

    @TypeConverter
    fun toThumbnail(json: String) : ThumbnailEntity {
        return Gson().fromJson(json, ThumbnailEntity::class.java)
    }
}