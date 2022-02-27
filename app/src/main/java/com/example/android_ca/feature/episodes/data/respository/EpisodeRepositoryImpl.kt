package com.example.android_ca.feature.episodes.data.respository

import com.example.android_ca.core.api.RickAndMortyApi
import com.example.android_ca.core.network.NetworkStateProvider
import com.example.android_ca.feature.episodes.EpisodeRepository
import com.example.android_ca.feature.episodes.data.local.EpisodeDao
import com.example.android_ca.feature.episodes.data.local.model.EpisodeCached
import com.example.android_ca.feature.episodes.domain.model.Episode

class EpisodeRepositoryImpl(
    private val api: RickAndMortyApi,
    private val episodeDao: EpisodeDao,
    private val networkStateProvider: NetworkStateProvider
) : EpisodeRepository {

    override suspend fun getEpisodes(): List<Episode> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getEpisodeFromRemote().also { saveEpisodeToLocal(it) }
        } else {
            getEpisodeFromLocal()
        }
    }

    private suspend fun getEpisodeFromRemote(): List<Episode> {
        return api.getEpisodes()
            .results
            .map { it.toEpisode() }
    }

    private suspend fun saveEpisodeToLocal(episodes: List<Episode>) {
        episodes.map { EpisodeCached(it) }
            .toTypedArray()
            .let { episodeDao.saveEpisode(*it) }
    }

    private suspend fun getEpisodeFromLocal(): List<Episode> {
        return episodeDao.getEpisodes().map { it.toEpisode() }
    }
}