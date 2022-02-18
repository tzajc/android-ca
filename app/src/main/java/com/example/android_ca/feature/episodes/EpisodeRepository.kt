package com.example.android_ca.feature.episodes

import com.example.android_ca.feature.episodes.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(): List<Episode>
}