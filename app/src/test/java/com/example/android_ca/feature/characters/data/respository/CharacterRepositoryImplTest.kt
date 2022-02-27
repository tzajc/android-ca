package com.example.android_ca.feature.characters.data.respository

import com.example.android_ca.core.api.RickAndMortyApi
import com.example.android_ca.core.api.model.CharacterResponse
import com.example.android_ca.core.network.NetworkStateProvider
import com.example.android_ca.feature.characters.CharacterRepository
import com.example.android_ca.feature.characters.data.local.CharacterDao
import com.example.android_ca.feature.characters.data.local.model.CharacterCached
import com.example.android_ca.mock.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CharacterRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN characters request THEN fetch characters form API`() {
        val api = mockk<RickAndMortyApi> {
            coEvery { getCharacters() } returns CharacterResponse.mock()
        }
        val characterDao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, characterDao, networkStateProvider)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { api.getCharacters() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetch WHEN characters request THEN save characters to local database`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getCharacters() } returns CharacterResponse.mock()
        }
        val characterDao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, characterDao, networkStateProvider)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { characterDao.saveCharacters(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN characters request THEN fetch characters from database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val characterDao = mockk<CharacterDao> {
            coEvery { getCharacters() } returns listOf(
                CharacterCached.mock(),
                CharacterCached.mock()
            )
        }
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns false
        }
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, characterDao, networkStateProvider)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { characterDao.getCharacters() }
    }
}