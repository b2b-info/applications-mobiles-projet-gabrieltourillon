package com.example.osrsdex.activities.selectloadout.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.models.Loadout

class ViewLoadoutsRecyclerAdapter(private var listLoadout:List<Loadout>, val onClick: (Loadout) -> Unit) : RecyclerView.Adapter<ViewLoadoutsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewLoadoutsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loadouts, parent, false)
        return ViewLoadoutsViewHolder(view, onClick)
    }


    override fun getItemCount(): Int {
        return listLoadout.size
    }

    override fun onBindViewHolder(holder: ViewLoadoutsViewHolder, position: Int) {
        holder.bind(listLoadout.get(position))
    }
}