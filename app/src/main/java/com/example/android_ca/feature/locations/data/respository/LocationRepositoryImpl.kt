package com.example.android_ca.feature.locations.data.respository

import com.example.android_ca.core.api.RickAndMortyApi
import com.example.android_ca.core.network.NetworkStateProvider
import com.example.android_ca.feature.locations.LocationRepository
import com.example.android_ca.feature.locations.data.local.LocationDao
import com.example.android_ca.feature.locations.data.local.model.LocationCached
import com.example.android_ca.feature.locations.domain.model.Location

class LocationRepositoryImpl(
    private val api: RickAndMortyApi,
    private val locationDao: LocationDao,
    private val networkStateProvider: NetworkStateProvider
) : LocationRepository {

    override suspend fun getLocations(): List<Location> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getLocationsFromRemote().also { saveLocation(it) }
        } else {
            getLocationsFromLocal()
        }
    }

    private suspend fun getLocationsFromRemote(): List<Location> {
        return api.getLocations()
            .results
            .map { it.toLocation() }

    }

    private suspend fun saveLocation(locations: List<Location>) {
        locations.map { LocationCached(it) }
            .toTypedArray()
            .let { locationDao.saveLocation(*it) }
    }

    private suspend fun getLocationsFromLocal(): List<Location> {
        return locationDao.getLocations().map { it.toLocation() }
    }
}