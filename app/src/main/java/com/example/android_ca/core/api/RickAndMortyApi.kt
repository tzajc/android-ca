package com.example.android_ca.core.api

import com.example.android_ca.core.api.model.CharacterResponse
import com.example.android_ca.core.api.model.EpisodeResponse
import com.example.android_ca.core.api.model.LocationResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("episode")
    suspend fun getEpisodes(): EpisodeResponse

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("location")
    suspend fun getLocations(): LocationResponse
}