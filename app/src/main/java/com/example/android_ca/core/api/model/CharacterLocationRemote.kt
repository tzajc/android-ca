package com.example.android_ca.core.api.model

import com.example.android_ca.feature.characters.domain.model.CharacterLocation
import com.google.gson.annotations.SerializedName


data class CharacterLocationRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    companion object

    fun toCharacterLocation() = CharacterLocation(
        name = name,
        url = url
    )
}