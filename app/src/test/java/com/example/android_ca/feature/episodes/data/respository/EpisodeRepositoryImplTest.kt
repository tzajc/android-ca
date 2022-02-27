package com.example.android_ca.feature.episodes.data.respository

import com.example.android_ca.core.api.RickAndMortyApi
import com.example.android_ca.core.api.model.EpisodeResponse
import com.example.android_ca.core.network.NetworkStateProvider
import com.example.android_ca.feature.episodes.EpisodeRepository
import com.example.android_ca.feature.episodes.data.local.EpisodeDao
import com.example.android_ca.feature.episodes.data.local.model.EpisodeCached
import com.example.android_ca.mock.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class EpisodeRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN episodes request THEN fetch episodes from API`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getEpisodes() } returns EpisodeResponse.mock()
        }
        val episodeDao = mockk<EpisodeDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, episodeDao, networkStateProvider)

        //when
        runBlocking { repository.getEpisodes() }

        //then
        coVerify { api.getEpisodes() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetch WHEN episodes request THEN save episodes to local database`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getEpisodes() } returns EpisodeResponse.mock()
        }
        val episodeDao = mockk<EpisodeDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, episodeDao, networkStateProvider)

        //when
        runBlocking { repository.getEpisodes() }

        //then
        coVerify { episodeDao.saveEpisode(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN episodes request THEN fetch episodes from database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val episodeDao = mockk<EpisodeDao> {
            coEvery { getEpisodes() } returns listOf(EpisodeCached.mock(), EpisodeCached.mock())
        }
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns false
        }
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, episodeDao, networkStateProvider)

        //when
        runBlocking { repository.getEpisodes() }

        //then
        coVerify { episodeDao.getEpisodes() }
    }
}