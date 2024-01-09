package com.example.osrsdex.activities.loadout.viewloadouts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.loadout.editloadout.EditLoadoutActivity
import com.example.osrsdex.activities.loadout.viewloadouts.recyclerview.ViewLoadoutsRecyclerAdapter
import com.example.osrsdex.models.Loadout

/**
 * Handles the recyclerview stuff of ViewLoadoutsActivity
 */
class ViewLoadoutsListFragment :Fragment(){

    companion object {
        fun newInstance() = ViewLoadoutsListFragment()
    }

    private val viewModel: ViewLoadoutsViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_loadouts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvLoadoutList)

        viewModel.loadoutList.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = ViewLoadoutsRecyclerAdapter(it) {loadout:Loadout -> clickEdit(loadout)}
                recyclerView.adapter = adapter
            }
        }
        if(resources.getBoolean(R.bool.is_list_loadout_grid_layout)) {
            recyclerView.layoutManager = GridLayoutManager(context, /*resources.getInteger(R.integer.grid_column_count)*/2)
        }
        else
        {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun clickEdit(loadout: Loadout)
    {
        val intent = Intent(activity, EditLoadoutActivity::class.java)
        startActivity(intent)
    }
}