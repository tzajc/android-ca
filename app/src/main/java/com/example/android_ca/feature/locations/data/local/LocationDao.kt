package com.example.android_ca.feature.locations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_ca.feature.locations.data.local.model.LocationCached

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocation(vararg location: LocationCached)

    @Query("SELECT * from LocationCached")
    suspend fun getLocations(): List<LocationCached>
}