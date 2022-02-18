package com.example.android_ca.feature.characters.presentation.model

import com.example.android_ca.feature.characters.domain.model.Character

data class CharacterDisplayable(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationDisplayable,
    val location: CharacterLocationDisplayable,
    val image: String,
    val episode: List<String>,
    val url: String
) {
    constructor(character: Character) : this(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        type = character.type,
        gender = character.gender,
        origin = CharacterLocationDisplayable(character.origin),
        location = CharacterLocationDisplayable(character.location),
        image = character.image,
        episode = character.episode,
        url = character.url
    )
}