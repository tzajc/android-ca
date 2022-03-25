package com.example.android_ca.feature.episodes.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ca.R
import com.example.android_ca.core.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<EpisodeViewModel>(R.layout.fragment_episode) {
    private val episodeAdapter: EpisodeAdapter by inject()

    private var recyclerView: RecyclerView? = null
    override val viewModel: EpisodeViewModel by viewModel()

    override fun initViews() {
        super.initViews()
        recyclerView = view?.findViewById(R.id.episode_list_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = episodeAdapter
    }

    override fun initObservers() {
        super.initObservers()
        observeEpisodes()
    }

    private fun observeEpisodes() {
        viewModel.episodes.observe(this) {
            episodeAdapter.setItems(it)
        }
    }
}