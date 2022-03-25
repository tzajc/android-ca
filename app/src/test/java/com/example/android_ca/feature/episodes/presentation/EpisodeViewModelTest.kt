package com.example.android_ca.feature.episodes.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.android_ca.core.base.UiState
import com.example.android_ca.feature.episodes.domain.GetEpisodesUseCase
import com.example.android_ca.feature.episodes.domain.model.Episode
import com.example.android_ca.mock.mock
import com.example.android_ca.utils.ViewModelTest
import getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import observeForTesting
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class EpisodeViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN episode live data is observed THEN set pending state`() {
        //given
        val useCase = mockk<GetEpisodesUseCase>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase)

        //when
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Pending
    }

    @Test
    fun `WHEN episode live data is observed THEN invoke use case to get episode`() {
        //given
        val useCase = mockk<GetEpisodesUseCase>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase)

        //when
        viewModel.episodes.observeForTesting()

        //then
        verify { useCase(Unit, viewModel.viewModelScope, any(), any()) }
    }

    @Test
    fun `GIVEN use case result is success WHEN episode live data is observed THEN set idle state AND set result in live data`() {
        val episodes = listOf(Episode.mock(), Episode.mock(), Episode.mock())
        val useCase = mockk<GetEpisodesUseCase> {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<Episode>>) -> Unit>()(Result.success(episodes))
            }
        }
        val viewModel = EpisodeViewModel(useCase)

        //when
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        viewModel.episodes.getOrAwaitValue().forEachIndexed { index, episodeDisplayable ->
            val episode = episodes[index]
            episodeDisplayable.name shouldBe episode.name
            episodeDisplayable.airDate shouldBe episode.airDate
            episodeDisplayable.characters shouldBe episode.characters
            episodeDisplayable.code shouldBe episode.code
            episodeDisplayable.url shouldBe episode.url
        }
    }

    @Test
    fun `GIVEN use case result is failure WHEN episode live data is observed THEN set idle state AND set error message in live data`() {
        val throwable = Throwable("Ops... Something went wrong")
        val useCase = mockk<GetEpisodesUseCase> {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<Episode>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val observer = mockk<Observer<String>>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase)

        //when
        viewModel.message.observeForever(observer)
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        verify { observer.onChanged(throwable.message) }
    }
}