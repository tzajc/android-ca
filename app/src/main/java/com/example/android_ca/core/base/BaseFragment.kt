package com.example.android_ca.core.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        bindViewModelToLifecycle()
    }

    /** initialize all view-related classes*/
    open fun initViews() {}

    open fun initObservers() {
        observeUiState()
        observeMessage()
    }

    /**handle Pending State*/
    open fun onPendingState() {

    }

    /**handle Idle State*/
    open fun onIdleState() {

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes stringRes: Int) {
        showToast(getString(stringRes))
    }

    private fun bindViewModelToLifecycle() {
        lifecycle.addObserver(viewModel)
    }

    private fun observeMessage() = viewModel.message.observe(viewLifecycleOwner) {
        showToast(it)
    }

    private fun observeUiState() = viewModel.uiState.observe(viewLifecycleOwner) {
        when (it) {
            UiState.Idle -> onIdleState()
            UiState.Pending -> onPendingState()
        }
    }
}