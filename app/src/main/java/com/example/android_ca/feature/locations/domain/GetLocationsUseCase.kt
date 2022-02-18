package com.example.android_ca.feature.locations.domain

import com.example.android_ca.core.base.UseCase
import com.example.android_ca.feature.locations.LocationRepository
import com.example.android_ca.feature.locations.domain.model.Location

class GetLocationsUseCase(private val repository: LocationRepository) :
    UseCase<List<Location>, Unit>() {
    override suspend fun action(params: Unit) = repository.getLocations()
}