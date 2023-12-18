package com.example.osrsdex.activities.selectloadout

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.models.Loadout

class LoadoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val name: TextView
    val description: TextView
    val btnEdit: Button
    val btnView: Button
    lateinit var currentLoadout: Loadout

    init
    {
        name = itemView.findViewById(R.id.tvLoadoutNameValue)
        description = itemView.findViewById(R.id.tvLoadoutDescriptionValue)
        btnEdit = itemView.findViewById(R.id.btnEditLoadout)
        btnView = itemView.findViewById(R.id.btnViewLoadout)
    }

    fun bind(loadout: Loadout)
    {
        currentLoadout = loadout
        name.text = loadout.name
        description.text = loadout.description
        btnEdit.setOnClickListener{
            clickEditLoadout(currentLoadout)
        }
        btnView.setOnClickListener()
        {
            clickViewLoadout(currentLoadout)
        }
    }
}

class LoadoutRecyclerAdapter(private val listLoadout:List<Loadout>) : RecyclerView.Adapter<LoadoutViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loadouts, parent, false)
        return LoadoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLoadout.size
    }

    override fun onBindViewHolder(holder: LoadoutViewHolder, position: Int) {
        holder.bind(listLoadout.get(position))
    }
}

fun clickEditLoadout(loadout: Loadout)
{
    Log.d(TAG, "clickEditLoadout: Bung")
}

fun clickViewLoadout(loadout: Loadout)
{
    Log.d(TAG, "clickEditLoadout: Bung")
}