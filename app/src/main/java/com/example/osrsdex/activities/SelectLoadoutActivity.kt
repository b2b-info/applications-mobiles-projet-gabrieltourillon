package com.example.osrsdex.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.selectloadout.*
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.db.Loadout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadoutViewModel(): ViewModel()
{
    val loadoutList: MutableList<Loadout> = mutableListOf()
}

const val LOADOUT = "LOADOUT"
const val TAG = "OSRSDEX"

class SelectLoadoutActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: LoadoutRecyclerAdapter
    val viewModel: LoadoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_loadout)

        recyclerView = findViewById(R.id.rvGetLoadoutsByPlayerName)

        adapter = LoadoutRecyclerAdapter(viewModel.loadoutList)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        if(viewModel.loadoutList.size == 0)
        {
            val dataBase = AppDatabase.getDatabase(applicationContext)

            lifecycleScope.launch {
                viewModel.loadoutList.clear()
                viewModel.loadoutList.addAll(withContext(Dispatchers.IO) {
                    dataBase.loadoutDAO().getAll()
                })
                adapter.notifyDataSetChanged()
            }
        }



    }
}