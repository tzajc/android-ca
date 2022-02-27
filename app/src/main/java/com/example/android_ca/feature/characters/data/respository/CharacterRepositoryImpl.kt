package com.example.android_ca.feature.characters.data.respository

import com.example.android_ca.core.api.RickAndMortyApi
import com.example.android_ca.core.network.NetworkStateProvider
import com.example.android_ca.feature.characters.CharacterRepository
import com.example.android_ca.feature.characters.data.local.CharacterDao
import com.example.android_ca.feature.characters.data.local.model.CharacterCached
import com.example.android_ca.feature.characters.domain.model.Character

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val characterDao: CharacterDao,
    private val networkStateProvider: NetworkStateProvider
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getCharactersFromRemote().also { saveCharacter(it) }
        } else {
            getCharactersFromLocal()
        }

    }

    private suspend fun getCharactersFromRemote(): List<Character> {
        return api.getCharacters()
            .results
            .map { it.toCharacter() }

    }

    private suspend fun saveCharacter(characters: List<Character>) {
        characters.map { CharacterCached(it) }
            .toTypedArray()
            .let { characterDao.saveCharacters(*it) }
    }

    private suspend fun getCharactersFromLocal(): List<Character> {
        return characterDao.getCharacters().map { it.toCharacter() }
    }
}