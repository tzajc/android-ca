package com.example.android_ca.feature.locations

import com.example.android_ca.feature.locations.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(): List<Location>
}