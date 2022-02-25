package com.example.android_ca.core.api.model

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("info") val info: ResponseInfo,
    @SerializedName("results") val results: List<EpisodeRemote>
) {
    companion object
}
