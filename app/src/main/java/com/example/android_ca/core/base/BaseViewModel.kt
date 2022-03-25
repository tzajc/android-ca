package com.example.android_ca.core.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {
    private val _message by lazy { LiveEvent<String>() }
    private val _uiState by lazy { MutableLiveData<UiState>(UiState.Idle) }

    val message: LiveEvent<String> by lazy { _message }
    val uiState: LiveData<UiState> by lazy { _uiState }

    private fun showMessage(message: String) {
        _message.value = message
    }

    protected fun setIdleState() {
        _uiState.value = UiState.Idle
    }

    protected fun setPendingState() {
        _uiState.value = UiState.Pending
    }

    protected fun handleFailure(throwable: Throwable) {
        throwable.message?.let { showMessage(it) }
    }

}