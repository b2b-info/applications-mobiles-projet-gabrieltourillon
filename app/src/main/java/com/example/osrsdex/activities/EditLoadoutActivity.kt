package com.example.osrsdex.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.osrsdex.R
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.db.Loadout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditLoadoutViewModel() : ViewModel()
{
    var loadout: Loadout? = null
}

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

        var playerNameString = playerName.text.toString()
        var loadoutNameString = loadoutName.text.toString()

        //Verify values:
        if(true/*playerName.text.isNotEmpty() && loadoutName.text.isNotEmpty()*/)
        {
            //Check if loadout already in db
            lifecycleScope.launch{
                val loadoutExists= (withContext(Dispatchers.IO) {dataBase.loadoutDAO().isLoadoutExists( "Gabeypoo","testloadout")})
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

        }
    }

    fun replaceLoadout()
    {
        val dataBase = AppDatabase.getDatabase(applicationContext)

        Log.d(TAG, "replaceLoadout: ")
    }

}