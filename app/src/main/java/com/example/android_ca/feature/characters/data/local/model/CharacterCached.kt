package com.example.android_ca.feature.characters.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_ca.feature.characters.domain.model.Character
import com.example.android_ca.feature.characters.domain.model.CharacterLocation

@Entity
data class CharacterCached(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationCached,
    val location: CharacterLocationCached,
    val image: String,
    val episode: List<String>,
    val url: String
) {
    constructor(character: Character) : this(
        character.id,
        character.name,
        character.status,
        character.species,
        character.type,
        character.gender,
        CharacterLocationCached(character.origin),
        CharacterLocationCached(character.location),
        character.image,
        character.episode,
        character.url,
    )

    companion object

    fun toCharacter() = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = CharacterLocation(origin.name, origin.url),
        location = CharacterLocation(location.name, location.url),
        image = image,
        episode = episode,
        url = url
    )
}
