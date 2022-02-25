package com.example.android_ca.mock

import com.example.android_ca.core.api.model.EpisodeRemote
import com.example.android_ca.core.api.model.EpisodeResponse
import com.example.android_ca.core.api.model.ResponseInfo
import com.example.android_ca.feature.episodes.data.local.model.EpisodeCached
import org.jetbrains.annotations.TestOnly


@TestOnly
fun EpisodeRemote.Companion.mock() = EpisodeRemote(
    id = 1,
    name = "Example name",
    airDate = "2022-02-25",
    code = "CODE",
    characters = emptyList(),
    url = "Example url",
    created = "2022-01-05"
)

@TestOnly
fun EpisodeResponse.Companion.mock() = EpisodeResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        EpisodeRemote.mock(),
        EpisodeRemote.mock(),
        EpisodeRemote.mock(),
        EpisodeRemote.mock()
    )
)

@TestOnly
fun EpisodeCached.Companion.mock() = EpisodeCached(
    id = 1,
    name = "Example name",
    airDate = "2022-02-25",
    code = "CODE",
    characters = emptyList(),
    url = "Example url"
)