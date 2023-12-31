package com.example.osrsdex.activities.selectloadout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.editloadoutactivity.EditLoadoutActivity
import com.example.osrsdex.activities.selectloadout.recyclerview.SelectLoadoutRecyclerAdapter
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.Loadout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectLoadoutActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SelectLoadoutRecyclerAdapter
    val viewModel: SelectLoadoutViewModel by viewModels()
    lateinit var etPlayerName: EditText
    lateinit var btnGetLoadouts: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_loadout)

        recyclerView = findViewById(R.id.rvGetLoadoutsByPlayerName)

        etPlayerName = findViewById(R.id.etGetLoadoutsByPlayerName)
        btnGetLoadouts = findViewById(R.id.btnGetLoadoutsByPlayerName)

        adapter = SelectLoadoutRecyclerAdapter(viewModel.loadoutList) { loadout:Loadout -> clickEdit(loadout)}

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnGetLoadouts.setOnClickListener()
        {
            btnGetLoadoutsOnClick()
        }
    }

    fun clickEdit(loadout: Loadout)
    {
        val intent = Intent(applicationContext, EditLoadoutActivity::class.java)
        startActivity(intent)
    }

    fun btnGetLoadoutsOnClick() {
        val playerNameString = etPlayerName.getText().toString()
        if (playerNameString.trim() == "") {
            //Empty Player Name
            Toast.makeText(this, "Please enter a player name", Toast.LENGTH_SHORT).show()
            return;
        } else {
            //We populate the view holder
            populateViewHolder(playerNameString)
        }
    }


    fun populateViewHolder(playerName: String) {
        val dataBase = AppDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            viewModel.loadoutList.clear()
            viewModel.loadoutList.addAll(withContext(Dispatchers.IO) {
                dataBase.loadoutDAO().getAllWherePlayerName(playerName)
            })
            adapter.notifyDataSetChanged()
        }

        if(viewModel.loadoutList.size == 0)
        {
            Toast.makeText(this, "No loadouts found belonging to player", Toast.LENGTH_LONG).show()
        }
    }


}