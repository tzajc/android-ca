package com.example.android_ca.feature.characters.presentation.model

import com.example.android_ca.feature.characters.domain.model.CharacterLocation

data class CharacterLocationDisplayable(
    val name: String,
    val url: String
) {
    constructor(characterLocation: CharacterLocation) : this(
        name = characterLocation.name,
        url = characterLocation.url
    )
}