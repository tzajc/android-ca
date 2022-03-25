package com.example.android_ca.feature.locations.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ca.R
import com.example.android_ca.core.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<LocationViewModel>(R.layout.fragment_location) {
    private val locationAdapter: LocationAdapter by inject()

    private var recyclerView: RecyclerView? = null
    override val viewModel: LocationViewModel by viewModel()

    override fun initViews() {
        super.initViews()
        recyclerView = view?.findViewById(R.id.locations_list_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = locationAdapter
    }

    override fun initObservers() {
        super.initObservers()
        observeLocations()
    }

    private fun observeLocations() {
        viewModel.locations.observe(this) {
            locationAdapter.setItems(it)
        }
    }
}