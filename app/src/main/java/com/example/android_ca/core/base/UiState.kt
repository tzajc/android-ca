package com.example.android_ca.core.base

sealed class UiState {
    object Idle : UiState()
    object Pending : UiState()
}
