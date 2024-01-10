package com.example.osrsdex.activities.loadout.editloadout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
import com.example.osrsdex.activities.loadout.viewloadouts.ViewLoadoutsActivity
import com.example.osrsdex.activities.main.MainActivity
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.CombatBonuses
import com.example.osrsdex.models.CombatLevels
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EditLoadoutActivity : AppCompatActivity() {
    private lateinit var fabSave: FloatingActionButton
    private lateinit var playerName: EditText
    private lateinit var loadoutName: EditText
    private lateinit var btnGetPlayerLevels:Button
    private val viewModel:EditLoadoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_loadout)

        val toolbar: Toolbar = findViewById(R.id.editLoadoutToolbar)
        setSupportActionBar(toolbar)

        playerName = findViewById(R.id.etEditLoadoutPlayerName)
        loadoutName = findViewById(R.id.etEditLoadoutLoadoutName)

        fabSave = findViewById(R.id.floatingActionButtonSave)
        fabSave.setOnClickListener()
        {
            onClickSave()
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.userMessage?.let {
                        Snackbar.make(findViewById(R.id.floatingActionButtonSave), it, Snackbar.LENGTH_LONG).show()
                        // Once the message is displayed and dismissed, notify the ViewModel.
                        viewModel.messageShown()
                    }
                    uiState.isNeedShowAlert?.let {
                        if(it)
                        {
                            setupAlertSaveReplaceLoadout()
                        }
                    }
                }
            }
        }
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
            R.id.action_new_loadout -> onMenuNewLoadout()
            else-> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun onMenuNewLoadout() {
        //Not sure if this is the right way to do this but this lets the user press < to go back to the activity, using finish just closes the activity so you cant go back
        startActivity(Intent(applicationContext, this::class.java))
    }

    private fun setupAlertSaveReplaceLoadout()
    {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(resources.getString(R.string.alert_save_loadout_title))
        alertBuilder.setMessage(resources.getString(R.string.alert_save_loadout_message))
        alertBuilder.setPositiveButton("Replace"){
            dialog, which ->
                viewModel.replaceLoadout()
                viewModel.alertShown()
        }
        .setNegativeButton("Cancel")
        {
            dialog, which ->
                viewModel.alertShown()
        }
        val dialog: AlertDialog = alertBuilder.create()
        dialog.show()
    }

    private fun onClickSave()
    {
        if(isRequiredFieldsNotBlank())
        {
            viewModel.onClickSaveLoadout(intDataMap = getLoadoutIntData(), stringDataMap = getLoadoutStringData())
            val imm = InputMethodManager.HIDE_IMPLICIT_ONLY
        }
        else{
            viewModel.displayMessage(resources.getString(R.string.edit_loadout_error_required_fields))
        }
    }



    //This is some trash, get rid of it for real data binding or just use an object with string fields instead of a map, it's so unsafe
    private fun getLoadoutStringData():Map<String,String>
    {
        ///EditText View String Variables///
        //Descriptors//
        val playerNameText: EditText = findViewById(R.id.etEditLoadoutPlayerName)
        val loadoutNameText: EditText = findViewById(R.id.etEditLoadoutLoadoutName)
        val loadoutDescriptionText: EditText = findViewById(R.id.etEditLoadoutLoadoutDescription)

        return mapOf<String,String>(
            "playerName" to playerNameText.text.toString(),
            "loadoutName" to loadoutNameText.text.toString(),
            "loadoutDescription" to loadoutDescriptionText.text.toString()
        )
    }

    /**
     * Not blank = true
     * Blank = false
     */
    private fun isRequiredFieldsNotBlank():Boolean
    {
        //Required fields
        val playerNameText: EditText = findViewById(R.id.etEditLoadoutPlayerName)
        val loadoutNameText: EditText = findViewById(R.id.etEditLoadoutLoadoutName)
        Log.d(TAG, "isRequiredFieldsNotBlank: |" + playerNameText.text.toString() +'|'+ loadoutNameText.text.toString()+'|')
        if(playerNameText.text.isNotBlank() && loadoutNameText.text.isNotBlank()) {
            return true
        }
        return false
    }

    private fun getLoadoutIntData():Map<String,String>
    {
        ////EditText View Int Variables////
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

        //Map of stat strings we wanna convert to int
        return mapOf<String,String>(
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
    }

}