package com.example.android_ca.feature.episodes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ca.R
import com.example.android_ca.feature.episodes.presentation.model.EpisodeDisplayable

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val episodes = mutableListOf<EpisodeDisplayable>()

    fun setItems(episodes: List<EpisodeDisplayable>) {
        if (episodes.isNotEmpty()) this.episodes.clear()

        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EpisodeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bind(episode)
    }

    override fun getItemCount() = episodes.size

    class EpisodeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.episode_item, parent, false)) {

        private val episodeCode: TextView = itemView.findViewById(R.id.episode_code)
        private val episodeName: TextView = itemView.findViewById(R.id.episode_name)
        private val episodeAirDate: TextView = itemView.findViewById(R.id.episode_air_date)

        fun bind(episode: EpisodeDisplayable) {
            episodeCode.text = episode.code
            episodeName.text = episode.name
            episodeAirDate.text = episode.airDate
        }

    }
}



