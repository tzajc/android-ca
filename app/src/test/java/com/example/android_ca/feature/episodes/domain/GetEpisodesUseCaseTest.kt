package com.example.android_ca.feature.episodes.domain

import com.example.android_ca.feature.episodes.EpisodeRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test

internal class GetEpisodesUseCaseTest {

    @Test
    fun `when use case is invoked than execute getEpisode method from repository`() {
        //given
        val repository = mockk<EpisodeRepository>(relaxed = true)
        val useCase = GetEpisodesUseCase(repository)
        //when
        useCase(
            params = Unit,
            scope = GlobalScope
        )
        //then
        coVerify { repository.getEpisodes() }
    }
}