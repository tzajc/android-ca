package com.example.android_ca.core.di

import com.example.android_ca.feature.characters.CharacterRepository
import com.example.android_ca.feature.characters.data.respository.CharacterRepositoryImpl
import com.example.android_ca.feature.characters.domain.GetCharactersUseCase
import com.example.android_ca.feature.characters.presentation.CharacterAdapter
import com.example.android_ca.feature.characters.presentation.CharacterFragment
import com.example.android_ca.feature.characters.presentation.CharacterViewModel
import com.example.android_ca.feature.episodes.EpisodeRepository
import com.example.android_ca.feature.episodes.data.respository.EpisodeRepositoryImpl
import com.example.android_ca.feature.episodes.domain.GetEpisodesUseCase
import com.example.android_ca.feature.episodes.presentation.EpisodeAdapter
import com.example.android_ca.feature.episodes.presentation.EpisodeFragment
import com.example.android_ca.feature.episodes.presentation.EpisodeViewModel
import com.example.android_ca.feature.locations.LocationRepository
import com.example.android_ca.feature.locations.data.respository.LocationRepositoryImpl
import com.example.android_ca.feature.locations.domain.GetLocationsUseCase
import com.example.android_ca.feature.locations.presentation.LocationAdapter
import com.example.android_ca.feature.locations.presentation.LocationFragment
import com.example.android_ca.feature.locations.presentation.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory<EpisodeRepository> { EpisodeRepositoryImpl(get(), get(), get()) }
    factory<LocationRepository> { LocationRepositoryImpl(get(), get(), get()) }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get()) }

    factory { GetEpisodesUseCase(get()) }
    factory { GetCharactersUseCase(get()) }
    factory { GetLocationsUseCase(get()) }

    factory { EpisodeFragment() }
    viewModel { EpisodeViewModel(get()) }
    factory { EpisodeAdapter() }

    factory { LocationFragment() }
    viewModel { LocationViewModel(get()) }
    factory { LocationAdapter() }

    factory { CharacterFragment() }
    viewModel { CharacterViewModel(get()) }
    factory { CharacterAdapter() }
}
