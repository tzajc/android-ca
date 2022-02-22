package com.example.android_ca.feature.episodes.domain

import com.example.android_ca.core.base.UseCase
import com.example.android_ca.feature.episodes.EpisodeRepository
import com.example.android_ca.feature.episodes.domain.model.Episode

class GetEpisodesUseCase(private val repository: EpisodeRepository) :
    UseCase<List<Episode>, Unit>() {
    override suspend fun action(params: Unit) = repository.getEpisodes()
}