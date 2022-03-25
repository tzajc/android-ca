package com.example.android_ca.feature.locations.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ca.R
import com.example.android_ca.feature.locations.presentation.model.LocationDisplayable

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val locations = mutableListOf<LocationDisplayable>()

    fun setItems(location: List<LocationDisplayable>) {
        if (location.isNotEmpty()) this.locations.clear()

        this.locations.addAll(location)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LocationViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location)
    }

    override fun getItemCount() = locations.size

    class LocationViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.location_item, parent, false)) {

        private val locationName: TextView = itemView.findViewById(R.id.location_name)
        private val locationType: TextView = itemView.findViewById(R.id.location_type)
        private val locationDimension: TextView = itemView.findViewById(R.id.location_dimension)

        fun bind(location: LocationDisplayable) {
            locationName.text = location.name
            locationType.text = location.type
            locationDimension.text = location.dimension
        }

    }
}



