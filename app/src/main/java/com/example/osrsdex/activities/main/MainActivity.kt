package com.example.osrsdex.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.activities.loadout.editloadout.EditLoadoutActivity
import com.example.osrsdex.activities.loadout.viewloadouts.ViewLoadoutsActivity
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.CombatLevels
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player
import com.example.osrsdex.network.HiScoreAPI
import com.example.osrsdex.network.HiScoreAPIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnViewLoadouts: Button = findViewById(R.id.btnPlayerLoadoutsViewLoadout)
        val btnCreateLoadout: Button = findViewById(R.id.btnPlayerLoadoutsCreate)
        val btnTester: Button = findViewById(R.id.btnTester)

        val toolbar:Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(toolbar)
        btnViewLoadouts.setOnClickListener(){onClickViewLoadouts()}
        btnCreateLoadout.setOnClickListener(){onClickCreateLoadout()}
        btnTester.setOnClickListener(){onClickTestButton()}

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_main_menu -> Toast.makeText(this, "Item1 sélectionné", Toast.LENGTH_SHORT).show()
            R.id.action_view_loadout -> Toast.makeText(this, "Item2 sélectionné", Toast.LENGTH_SHORT).show()
            R.id.action_edit_loadout -> Toast.makeText(this, "Item3 sélectionné", Toast.LENGTH_SHORT).show()
            else-> return super.onOptionsItemSelected(item)
        }
        return true
    }*/

    private fun onClickCreateLoadout(){
        val intent = Intent(applicationContext, EditLoadoutActivity::class.java)
        startActivity(intent)
    }

    private fun onClickViewLoadouts()
    {
        val intent = Intent(applicationContext, ViewLoadoutsActivity::class.java)
        startActivity(intent)
    }

    private fun onClickTestButton()
    {

        val client = HiScoreAPIClient.getInstance().create(HiScoreAPI::class.java)
        lifecycleScope.launch {
            try
            {
                val response = withContext(Dispatchers.IO){client.getMyStatsByGameMode()}
                if(response.isSuccessful)
                {
                    val body = response.body()
                    if(body != null)
                    {
                        Log.d(TAG, "onClickTestButton: Successful request:" + body.toString())
                    }
                    else
                    {
                        Log.e(TAG, "onClickTestButton: didnt work")
                    }
                }
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
    }

    private fun testPlayerRoom()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)

        val player: Player= Player("Gabeypoo", CombatLevels(null,76,64,75,66,76,77,59))
        //val loadout = Loadout("Bungus", "")
        Log.d(TAG, "onClickTestButton: ${player.combatLevels.combatLevel}")
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                dataBase.playerDAO().insertPlayer(player)
            }
        }
        Toast.makeText(this, "Loadout succesfully saved!", Toast.LENGTH_SHORT).show()
    }

}