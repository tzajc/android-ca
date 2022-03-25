package com.example.android_ca.feature.episodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.android_ca.core.base.BaseViewModel
import com.example.android_ca.feature.episodes.domain.GetEpisodesUseCase
import com.example.android_ca.feature.episodes.domain.model.Episode
import com.example.android_ca.feature.episodes.presentation.model.EpisodeDisplayable

class EpisodeViewModel(private val getEpisodesUseCase: GetEpisodesUseCase) : BaseViewModel() {

    private val _episodes by lazy {
        MutableLiveData<List<Episode>>()
            .also { getEpisodes(it) }
    }

    val episodes: LiveData<List<EpisodeDisplayable>> by lazy {
        _episodes.map { episodes ->
            episodes.map { EpisodeDisplayable(it) }
        }
    }


    private fun getEpisodes(episodesLiveData: MutableLiveData<List<Episode>>) {
        setPendingState()
        getEpisodesUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { episodesLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}
