package com.example.android_ca.feature.characters.data.local.model

import com.example.android_ca.feature.characters.domain.model.CharacterLocation

data class CharacterLocationCached(
    val name: String,
    val url: String
) {
    constructor(characterLocation: CharacterLocation) : this(
        characterLocation.name,
        characterLocation.url
    )

    companion object

    fun toCharacterLocation() = CharacterLocation(
        name = name,
        url = url
    )
}