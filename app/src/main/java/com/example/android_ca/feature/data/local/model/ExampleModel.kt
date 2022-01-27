package com.example.android_ca.feature.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
class ExampleModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}