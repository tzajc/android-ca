package com.example.android_ca.feature.characters.data.local.model

import com.example.android_ca.feature.characters.domain.model.CharacterLocation
import com.google.gson.annotations.SerializedName


data class CharacterLocationCached(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
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