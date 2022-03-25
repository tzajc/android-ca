package com.example.android_ca.feature.characters.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_ca.R
import com.example.android_ca.feature.characters.presentation.model.CharacterDisplayable

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<CharacterDisplayable>()

    fun setItems(characters: List<CharacterDisplayable>) {
        if (characters.isNotEmpty()) this.characters.clear()

        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount() = characters.size

    class CharacterViewHolder(inflater: LayoutInflater, private val parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.character_item, parent, false)) {

        private val characterName: TextView = itemView.findViewById(R.id.character_name)
        private val characterSpecies: TextView = itemView.findViewById(R.id.character_species)
        private val characterStatus: TextView = itemView.findViewById(R.id.character_status)
        private val characterImage: ImageView = itemView.findViewById(R.id.character_image)

        fun bind(character: CharacterDisplayable) {
            characterName.text = character.name
            characterSpecies.text = character.species
            characterStatus.text = character.status
            Glide.with(parent.context).load(character.image).into(characterImage)
        }
    }
}



