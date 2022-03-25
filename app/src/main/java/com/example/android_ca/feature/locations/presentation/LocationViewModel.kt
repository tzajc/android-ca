package com.example.android_ca.feature.locations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.android_ca.core.base.BaseViewModel
import com.example.android_ca.feature.locations.domain.GetLocationsUseCase
import com.example.android_ca.feature.locations.domain.model.Location
import com.example.android_ca.feature.locations.presentation.model.LocationDisplayable

class LocationViewModel(private val getLocationsUseCase: GetLocationsUseCase) : BaseViewModel() {

    private val _locations by lazy {
        MutableLiveData<List<Location>>()
            .also { getLocations(it) }
    }

    val locations: LiveData<List<LocationDisplayable>> by lazy {
        _locations.map { locations ->
            locations.map { LocationDisplayable(it) }
        }
    }

    private fun getLocations(locationsLiveData: MutableLiveData<List<Location>>) {
        setPendingState()
        getLocationsUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { locationsLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}