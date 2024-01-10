package com.example.osrsdex.activities.loadout.viewloadouts.recyclerview

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.models.Loadout

class ViewLoadoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val name: TextView
    val description: TextView
    lateinit var currentLoadout: Loadout

    init
    {
        name = itemView.findViewById(R.id.tvLoadoutNameValue)
        description = itemView.findViewById(R.id.tvLoadoutDescriptionValue)
    }

    fun bind(loadout: Loadout)
    {
        currentLoadout = loadout
        name.text = loadout.loadoutName
        description.text = loadout.description
    }
}