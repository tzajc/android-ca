package com.example.android_ca.feature.data.remote.model

import com.example.android_ca.feature.characters.domain.model.CharacterLocation
import com.google.gson.annotations.SerializedName


data class CharacterLocationRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toCharacterLocation() = CharacterLocation(
        name = name,
        url = url
    )
}