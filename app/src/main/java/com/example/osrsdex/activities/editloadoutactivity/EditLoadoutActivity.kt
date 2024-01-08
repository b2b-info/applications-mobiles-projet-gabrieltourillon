package com.example.osrsdex.activities.editloadoutactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.activities.mainactivity.MainActivity
import com.example.osrsdex.activities.selectloadout.ViewLoadoutsActivity
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.CombatBonuses
import com.example.osrsdex.models.Loadout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditLoadoutActivity : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var playerName: EditText
    private lateinit var loadoutName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_loadout)

        val toolbar: Toolbar = findViewById(R.id.editToolbar)
        setSupportActionBar(toolbar)

        btnSave = findViewById(R.id.btnEditLoadoutSave)
        btnSave.setOnClickListener()
        {
            onClickSave()
        }
        playerName = findViewById(R.id.etEditLoadoutPlayerName)
        loadoutName = findViewById(R.id.etEditLoadoutLoadoutName)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_loadout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_main_menu -> startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.action_view_loadouts ->  startActivity(Intent(applicationContext, ViewLoadoutsActivity::class.java))
            R.id.action_new_loadout -> newLoadout()
            else-> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun newLoadout() {
        /*TODO("Implement: " +
                "Ask if user is sure he wants to reset the fields")*/
        //Not sure if this is the right way to do this but this lets the user press < to go back to the activity, using finish just closes the activity so you cant go back
        startActivity(Intent(applicationContext, this::class.java))
    }


    fun onClickSave()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)

        //Verify values:
        if(playerName.text.isNotEmpty() && loadoutName.text.isNotEmpty())
        {
            //Check if loadout already in db
            lifecycleScope.launch{
                val loadoutExists= (withContext(Dispatchers.IO) {dataBase.loadoutDAO().isLoadoutExists( createLoadout().loadoutPlayerName, createLoadout().loadoutName)})
                if(loadoutExists)
                {
                    Log.d(TAG, "onClickSave: Exists")
                    setupAlertSaveReplaceLoadout()
                }
                else
                {
                    Log.d(TAG, "onClickSave: Doesn't exist")
                    saveLoadout()
                }

            }


        }

    }

    fun setupAlertSaveReplaceLoadout()
    {
        var alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Replace Loadout?")
        alertBuilder.setMessage("A loadout with the same player and same name already exists in the database, would you like to overwrite it")
        alertBuilder.setPositiveButton("Replace"){
            dialog, which ->
            replaceLoadout()
        }
        .setNegativeButton("Cancel")
        {
                dialog, which ->
                //Don't do anything
        }

        val dialog: AlertDialog = alertBuilder.create()
        dialog.show()
    }

    fun saveLoadout()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)
        Log.d(TAG, "saveLoadout: ")

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val loadout = createLoadout()
                dataBase.loadoutDAO().insertLoadout(loadout)
            }
        }
        Toast.makeText(this, "Loadout succesfully saved!", Toast.LENGTH_SHORT).show()
    }

    fun replaceLoadout()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)
        Log.d(TAG, "replaceLoadout: ")

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val loadout = createLoadout()
                dataBase.loadoutDAO().updateLoadout(loadout)

            }
        }
        Toast.makeText(this, "Loadout succesfully replaced!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Returns true if valid, false if not valid
     */
    fun validateLoadout():Boolean
    {
        //Make sure our pk values aren't empty
        if(playerName.text.isNotEmpty() && loadoutName.text.isNotEmpty())
        {
            return true
        }
        else
        {
            //Tell user that the pk values are required
            Toast.makeText(this, "Player name and Loadout name are required", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun createLoadout():Loadout
    {
        ////EditText View Variables////
        //Descriptors//
        val playerNameText: EditText = findViewById(R.id.etEditLoadoutPlayerName)
        val loadoutNameText: EditText = findViewById(R.id.etEditLoadoutLoadoutName)
        val loadoutDescriptionText: EditText = findViewById(R.id.etEditLoadoutLoadoutDescription)
        ///Combat Stats///
        //Offensive//
        val bnsStbText: EditText = findViewById(R.id.etEditLoadoutOffensiveStb)
        val bnsSlsText: EditText = findViewById(R.id.etEditLoadoutOffensiveSls)
        val bnsCrsText: EditText = findViewById(R.id.etEditLoadoutOffensiveCrs)
        val bnsMagText: EditText = findViewById(R.id.etEditLoadoutOffensiveMag)
        val bnsRngText: EditText = findViewById(R.id.etEditLoadoutOffensiveRng)
        val bnsStrMelText: EditText = findViewById(R.id.etEditLoadoutOffensiveStrMel)
        val bnsStrMagText: EditText = findViewById(R.id.etEditLoadoutOffensiveStrMag)
        val bnsStrRngText: EditText = findViewById(R.id.etEditLoadoutOffensiveStrRng)
        //Defensive//
        val defStbText: EditText = findViewById(R.id.etEditLoadoutDefensiveStb)
        val defSlsText: EditText = findViewById(R.id.etEditLoadoutDefensiveSls)
        val defCrsText: EditText = findViewById(R.id.etEditLoadoutDefensiveCrs)
        val defMagText: EditText = findViewById(R.id.etEditLoadoutDefensiveMag)
        val defRngText: EditText = findViewById(R.id.etEditLoadoutDefensiveRng)

        //Not implemented yet
        val atkSpeed = 0
        val bnsPry = 0
        val atkStyle = "Slash"

        //Map of stat strings we wanna convert to int
        val mapStatStrings = mapOf<String,String>(
            "bnsStb" to bnsStbText.text.toString(),
            "bnsSls" to bnsSlsText.text.toString(),
            "bnsCrs" to bnsCrsText.text.toString(),
            "bnsMag" to bnsMagText.text.toString(),
            "bnsRng" to bnsRngText.text.toString(),
            "bnsStrMel" to bnsStrMelText.text.toString(),
            "bnsStrMag" to bnsStrMagText.text.toString(),
            "bnsStrRng" to bnsStrRngText.text.toString(),
            "defStb" to defStbText.text.toString(),
            "defSls" to defSlsText.text.toString(),
            "defCrs" to defCrsText.text.toString(),
            "defMag" to defMagText.text.toString(),
            "defRng" to defRngText.text.toString()
        )

        val mapStats = mutableMapOf<String, Int>()

        for (entry in mapStatStrings.entries.iterator())
        {
            Log.d(TAG, "createLoadout: " + entry.value.toString() + " value:" + entry.value.isNotBlank() )
            //If the EditText is not blank
            if(entry.value.isNotBlank() && entry.value.isNotEmpty())
            {
                mapStats[entry.key] = entry.value.toInt()
            }
            else
            {
                mapStats[entry.key] = 0
            }
        }


        ///Create Stats object
        val stats = CombatBonuses(
            mapStats["bnsStb"],
            mapStats["bnsSls"],
            mapStats["bnsCrs"],
            mapStats["bnsMag"],
            mapStats["bnsRng"],
            mapStats["bnsStrMel"],
            mapStats["bnsStrMag"],
            mapStats["bnsStrRng"],
            mapStats["defStb"],
            mapStats["defSls"],
            mapStats["defCrs"],
            mapStats["defMag"],
            mapStats["defRng"],
            atkSpeed,
            bnsPry
        )

        return Loadout(
            loadoutNameText.text.toString(),
            playerNameText.text.toString(),
            loadoutDescriptionText.text.toString(),
            stats
            )
    }

}