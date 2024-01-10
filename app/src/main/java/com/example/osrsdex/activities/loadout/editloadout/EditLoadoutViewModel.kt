package com.example.osrsdex.activities.loadout.editloadout

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.HiScoreDataClass
import com.example.osrsdex.R
import com.example.osrsdex.TAG
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.CombatLevels
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player
import com.example.osrsdex.network.HiScoreAPI
import com.example.osrsdex.network.HiScoreAPIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditLoadoutViewModel(application: Application): AndroidViewModel(application){
    private var _shownPlayer: MutableLiveData<Player> = MutableLiveData()
    private var _loadout: MutableLiveData<Loadout> = MutableLiveData()
    val shownPlayer: LiveData<Player> = _shownPlayer
    val loadout: LiveData<Loadout> = _loadout

    //UIState for displaying snackbars
    private var _uiState = MutableStateFlow(EditLoadoutUIState())
    val uiState: StateFlow<EditLoadoutUIState> = _uiState

    fun displayMessage(message:String)
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = message) }
    }

    fun messageShown()
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = null) }
    }

    fun displayAlert()
    {
        _uiState.update{ currentUIState -> currentUIState.copy(isNeedShowAlert = true)}
    }

    fun alertShown()
    {
        _uiState.update { currentUIState -> currentUIState.copy(isNeedShowAlert = false) }
    }

    fun onClickSaveLoadout(intDataMap:Map<String,String>, stringDataMap:Map<String,String>)
    {
        val dataBind = LoadoutDataBinding(intDataMap = intDataMap, stringDataMap = stringDataMap)
        if(dataBind.validateData())
        {
            val currentLoadout:Loadout = dataBind.createLoadout()
            val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
            //Check if loadout in db
            viewModelScope.launch(Dispatchers.IO){
                if (dataBase.loadoutDAO().isLoadoutExists(loadoutName = currentLoadout.loadoutName, loadoutPlayerName = currentLoadout.loadoutPlayerName))
                {
                    //Loadout exists so we need to store it in _loadout and ask the user if they want to replace
                    withContext(Dispatchers.Main) { _loadout.value = currentLoadout }
                        displayAlert()
                }else{
                    insertLoadout(loadout = currentLoadout)
                }
            }
        }else{
            displayMessage(getApplication<Application>().getString(R.string.edit_loadout_error_validation))
        }
    }

    private fun insertLoadout(loadout: Loadout)
    {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        viewModelScope.launch(Dispatchers.IO) {
            val player = getCurrentPlayer(playerName = loadout.loadoutPlayerName)
            if(dataBase.loadoutDAO().insertLoadoutWithPlayer(loadout = loadout, player = player))
            {
                displayMessage(getApplication<Application>().getString(R.string.edit_loadout_success_insert))
            }else{
                displayMessage(getApplication<Application>().getString(R.string.edit_loadout_error_insert))
            }
        }
    }

    fun onClickReplaceLoadout()
    {
        val insertedLoadout:Loadout? = _loadout.value
        if(insertedLoadout != null)
        {
            viewModelScope.launch(Dispatchers.IO) {
                replaceLoadout(insertedLoadout)
            }
        }
        else
        {
            displayMessage(getApplication<Application>().getString(R.string.edit_loadout_error_replace_null))
        }
    }

    private suspend fun replaceLoadout(loadout: Loadout)
    {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        val player = getCurrentPlayer(loadout.loadoutPlayerName)

        if (dataBase.loadoutDAO().updateLoadoutWithPlayer(loadout, player)) {
            displayMessage(getApplication<Application>().getString(R.string.edit_loadout_success_replace))
        }
    }

    /**
     * Tries to get player levels from the api or db and show them thru the object shownPlayer
     * Tries to get levels from api, if that fails tries to get them from the db if that fails uses CombatLevel() constructor
     */
    private suspend fun getCurrentPlayer(playerName: String):Player
    {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        var player: Player? = getPlayerWithLevelsAPI(playerName = playerName)
        if (player == null)
        {
            val playerCheck = dataBase.playerDAO().getPlayer(playerName = playerName)
            //WE ONLY NEED TO TELL USER THERE WAS AN ISSUE GETTING LEVELS IF PLAYER LEVELS WERE NEVER PUT IN
            //We use the fact that in OSRS it's impossible for combatLevel to be 0 to check if they were ever gotten from the API and put in our db
            if(playerCheck?.combatLevels?.combatLevel != null && playerCheck.combatLevels.combatLevel!! > 0) {
                player = playerCheck
            } else {
                player = Player(playerName = playerName, CombatLevels())
                withContext(Dispatchers.Main){ displayMessage(getApplication<Application>().getString(R.string.edit_loadout_no_levels))}
            }
        }
        //Show player
        withContext(Dispatchers.Main) {updateShownPlayer(player)}
        //Return player
        return player
    }


    private suspend fun getPlayerWithLevelsAPI(playerName: String):Player? {
        val client = HiScoreAPIClient.getInstance().create(HiScoreAPI::class.java)
        var playerReturned:Player? = null
        try
        {
            val response = client.getStatsByGameMode(playerName)
            if(response.isSuccessful)
            {
                val body = response.body()
                if(body != null)
                {
                    val highScoreDataClass:HiScoreDataClass? = response.body()
                    val player = Player(playerName=playerName,combatLevels = CombatLevels(highScoreDataClass?.skills))
                    playerReturned = player
                }
            }
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
        return playerReturned
    }

    private fun updateShownPlayer(player: Player)
    {
        _shownPlayer.value = player
    }
}