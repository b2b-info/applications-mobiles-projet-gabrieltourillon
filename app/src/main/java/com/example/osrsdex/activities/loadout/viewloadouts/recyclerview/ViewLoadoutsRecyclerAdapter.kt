package com.example.osrsdex.activities.loadout.viewloadouts.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.models.Loadout

class ViewLoadoutsRecyclerAdapter(private var listLoadout:List<Loadout>) : RecyclerView.Adapter<ViewLoadoutsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewLoadoutsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_loadouts_list_item, parent, false)
        return ViewLoadoutsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewLoadoutsViewHolder, position: Int) {
        holder.bind(listLoadout[position])
    }

    override fun getItemCount(): Int {
        return listLoadout.size
    }
}