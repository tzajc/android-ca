package com.example.android_ca.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_ca.feature.data.local.dao.ExampleDAO
import com.example.android_ca.feature.data.local.model.ExampleModel

@Database(
    entities = arrayOf(ExampleModel::class),
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract val exampleDAO: ExampleDAO
}