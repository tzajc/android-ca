package com.example.android_ca.core.network

interface NetworkStateProvider {
    fun isNetworkAvailable(): Boolean
}