package com.example.android_ca.feature.characters

import com.example.android_ca.feature.characters.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}