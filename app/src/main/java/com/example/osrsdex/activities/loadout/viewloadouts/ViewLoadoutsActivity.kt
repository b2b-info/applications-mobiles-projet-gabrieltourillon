package com.example.osrsdex.activities.loadout.viewloadouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osrsdex.R
import com.example.osrsdex.activities.loadout.editloadout.EditLoadoutActivity
import com.example.osrsdex.activities.main.MainActivity
import com.example.osrsdex.activities.loadout.viewloadouts.recyclerview.ViewLoadoutsRecyclerAdapter
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.Loadout
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewLoadoutsActivity : AppCompatActivity() {

    val viewModel: ViewLoadoutsViewModel by viewModels()
    lateinit var etPlayerName: EditText
    lateinit var btnGetLoadouts: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_loadouts)

        val toolbar: Toolbar = findViewById(R.id.loadoutListToolbar)
        setSupportActionBar(toolbar)

        etPlayerName = findViewById(R.id.etGetLoadoutsByPlayerName)
        btnGetLoadouts = findViewById(R.id.btnGetLoadoutsByPlayerName)

        btnGetLoadouts.setOnClickListener()
        {
            btnGetLoadoutsOnClick()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.userMessage?.let {
                        Snackbar.make(findViewById(R.id.etGetLoadoutsByPlayerName), it, Snackbar.LENGTH_LONG).show()
                        // Once the message is displayed and dismissed, notify the ViewModel.
                        viewModel.messageShown()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_view_loadouts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_main_menu -> startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.action_view_loadouts ->  startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.action_new_loadout -> startActivity(Intent(applicationContext, EditLoadoutActivity::class.java))
            else-> return super.onOptionsItemSelected(item)
        }
        return true
    }


    private fun btnGetLoadoutsOnClick() {
        val playerNameString = etPlayerName.text.toString()
        viewModel.updateLoadoutList(playerName = playerNameString)
    }


}