package com.example.osrsdex.activities.editloadoutactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.CombatStats
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
        btnSave = findViewById(R.id.btnEditLoadoutSave)
        btnSave.setOnClickListener()
        {
            onClickSave()
        }
        playerName = findViewById(R.id.etEditLoadoutPlayerName)
        loadoutName = findViewById(R.id.etEditLoadoutLoadoutName)

    }

    fun onClickSave()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)

        //Verify values:
        if(playerName.text.isNotEmpty() && loadoutName.text.isNotEmpty())
        {
            //Check if loadout already in db
            lifecycleScope.launch{
                val loadoutExists= (withContext(Dispatchers.IO) {dataBase.loadoutDAO().isLoadoutExists( createLoadout().playerName, createLoadout().loadoutName)})
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
        //Levels//
        val lvlHpText: EditText = findViewById(R.id.etEditLoadoutLevelsHP)
        val lvlAtkText: EditText = findViewById(R.id.etEditLoadoutLevelsAtk)
        val lvlStrText: EditText = findViewById(R.id.etEditLoadoutLevelsStr)
        val lvlDefText: EditText = findViewById(R.id.etEditLoadoutLevelsDef)
        val lvlMagText: EditText = findViewById(R.id.etEditLoadoutLevelsMag)
        val lvlRngText: EditText = findViewById(R.id.etEditLoadoutLevelsRng)
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
            "lvlHp" to lvlHpText.text.toString(),
            "lvlAtk" to lvlAtkText.text.toString(),
            "lvlStr" to lvlStrText.text.toString(),
            "lvlDef" to lvlDefText.text.toString(),
            "lvlMag" to lvlMagText.text.toString(),
            "lvlRng" to lvlRngText.text.toString(),
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
                mapStats.put(entry.key, entry.value.toInt())
            }
            else
            {
                mapStats.put(entry.key, 0)
            }
        }


        ///Create Stats object
        val stats = CombatStats(
            mapStats["lvlHp"],
            mapStats["lvlAtk"],
            mapStats["lvlStr"],
            mapStats["lvlDef"],
            mapStats["lvlMag"],
            mapStats["lvlRng"],
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
            stats,
            atkStyle
            )
    }

}