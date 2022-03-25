package com.example.android_ca.feature.characters.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ca.R
import com.example.android_ca.core.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<CharacterViewModel>(R.layout.fragment_character) {
    private val characterAdapter: CharacterAdapter by inject()

    private var recyclerView: RecyclerView? = null
    override val viewModel: CharacterViewModel by viewModel()

    override fun initViews() {
        super.initViews()
        recyclerView = view?.findViewById(R.id.character_list_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = characterAdapter
    }

    override fun initObservers() {
        super.initObservers()
        observeCharacters()
    }

    private fun observeCharacters() {
        viewModel.characters.observe(this) {
            characterAdapter.setItems(it)
        }
    }
}