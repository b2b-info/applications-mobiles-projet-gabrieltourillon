package com.example.osrsdex.activities.selectloadout

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.EditLoadoutActivity
import com.example.osrsdex.activities.SelectLoadoutActivity
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.db.Loadout


class LoadoutViewHolder(itemView: View, onClick:(Loadout) -> Unit) : RecyclerView.ViewHolder(itemView)
{
    val name: TextView
    val description: TextView
    val btnEdit: Button
    lateinit var currentLoadout: Loadout

    init
    {
        name = itemView.findViewById(R.id.tvLoadoutNameValue)
        description = itemView.findViewById(R.id.tvLoadoutDescriptionValue)
        btnEdit = itemView.findViewById(R.id.btnEditLoadout)
        btnEdit.setOnClickListener{
            currentLoadout?.let(onClick)
        }
    }

    fun bind(loadout: Loadout)
    {
        currentLoadout = loadout
        name.text = loadout.loadoutName
        description.text = loadout.description

    }
}

class LoadoutRecyclerAdapter(private var listLoadout:List<Loadout>, val onClick: (Loadout) -> Unit) : RecyclerView.Adapter<LoadoutViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loadouts, parent, false)
        return LoadoutViewHolder(view, onClick)
    }


    override fun getItemCount(): Int {
        return listLoadout.size
    }

    override fun onBindViewHolder(holder: LoadoutViewHolder, position: Int) {
        holder.bind(listLoadout.get(position))
    }
}