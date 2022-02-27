package com.example.android_ca.mock

import com.example.android_ca.core.api.model.CharacterLocationRemote
import com.example.android_ca.core.api.model.CharacterRemote
import com.example.android_ca.core.api.model.CharacterResponse
import com.example.android_ca.core.api.model.ResponseInfo
import com.example.android_ca.feature.characters.data.local.model.CharacterCached
import com.example.android_ca.feature.characters.data.local.model.CharacterLocationCached
import org.jetbrains.annotations.TestOnly

@TestOnly
fun CharacterRemote.Companion.mock() = CharacterRemote(
    id = 1,
    name = "Example name",
    status = "Example status",
    species = "Example species",
    type = "Example type",
    gender = "Example gender",
    origin = CharacterLocationRemote.mock(),
    location = CharacterLocationRemote.mock(),
    episode = listOf("Episode 1", "Episode 2"),
    image = "Example image",
    url = "Example url",
    created = "2022-01-05"
)

@TestOnly
fun CharacterLocationRemote.Companion.mock() = CharacterLocationRemote(
    name = "Example name",
    url = "Example url",
)

@TestOnly
fun CharacterResponse.Companion.mock() = CharacterResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        CharacterRemote.mock(),
        CharacterRemote.mock(),
        CharacterRemote.mock(),
        CharacterRemote.mock()
    )
)

@TestOnly
fun CharacterLocationCached.Companion.mock() = CharacterLocationCached(
    name = "Example name",
    url = "Example url",
)

@TestOnly
fun CharacterCached.Companion.mock() = CharacterCached(
    id = 1,
    name = "Example name",
    status = "Example status",
    species = "Example species",
    type = "Example type",
    gender = "Example gender",
    origin = CharacterLocationCached.mock(),
    location = CharacterLocationCached.mock(),
    episode = listOf("Episode 1", "Episode 2"),
    image = "Example image",
    url = "Example url"
)