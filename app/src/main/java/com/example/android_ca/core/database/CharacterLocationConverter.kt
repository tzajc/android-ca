package com.example.android_ca.core.database

import androidx.room.TypeConverter
import com.example.android_ca.feature.characters.data.local.model.CharacterLocationCached
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterLocationConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toJson(data: CharacterLocationCached): String {
            return Gson().toJson(data)
        }

        @TypeConverter
        @JvmStatic
        fun fromJson(json: String): CharacterLocationCached {
            return Gson().fromJson(json, object : TypeToken<CharacterLocationCached>() {}.type)
        }
    }
}