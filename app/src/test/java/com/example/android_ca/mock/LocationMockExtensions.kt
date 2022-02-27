package com.example.android_ca.mock

import com.example.android_ca.core.api.model.LocationRemote
import com.example.android_ca.core.api.model.LocationResponse
import com.example.android_ca.core.api.model.ResponseInfo
import com.example.android_ca.feature.locations.data.local.model.LocationCached
import org.jetbrains.annotations.TestOnly

@TestOnly
fun LocationRemote.Companion.mock() = LocationRemote(
    id = 1,
    name = "Example name",
    type = "Example type",
    dimension = "Example dimension",
    residents = listOf("Residents 1", "Residents 2", "Residents 3"),
    url = "Example url",
    created = "2022-01-05"
)

@TestOnly
fun LocationResponse.Companion.mock() = LocationResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        LocationRemote.mock(),
        LocationRemote.mock(),
        LocationRemote.mock(),
        LocationRemote.mock()
    )
)

@TestOnly
fun LocationCached.Companion.mock() = LocationCached(
    id = 1,
    name = "Example name",
    type = "Example type",
    dimension = "Example dimension",
    residents = listOf("Residents 1", "Residents 2", "Residents 3"),
    url = "Example url"

)