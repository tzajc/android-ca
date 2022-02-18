package com.example.android_ca.feature.characters.domain

import com.example.android_ca.core.base.UseCase
import com.example.android_ca.feature.characters.CharacterRepository
import com.example.android_ca.feature.characters.domain.model.Character

class GetCharactersUseCase(private val repository: CharacterRepository) :
    UseCase<List<Character>, Unit>() {
    override suspend fun action(params: Unit) = repository.getCharacters()
}