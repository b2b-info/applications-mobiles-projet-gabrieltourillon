package com.example.osrsdex.activities.loadout.editloadout

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.HiScoreDataClass
import com.example.osrsdex.R
import com.example.osrsdex.activities.TAG
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
    private var _player: MutableLiveData<Player> = MutableLiveData()
    private var _loadout: MutableLiveData<Loadout> = MutableLiveData()
    //
    val player: LiveData<Player> = MutableLiveData()
    val loadout: LiveData<Loadout> = MutableLiveData()

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
            viewModelScope.launch{
                if(withContext(Dispatchers.IO) {dataBase.loadoutDAO().isLoadoutExists(loadoutName = currentLoadout.loadoutName, loadoutPlayerName = currentLoadout.loadoutPlayerName)})
                {
                    //Loadout exists so we need to store it in _loadout and ask the user if they want to replace
                    displayAlert()
                }else{
                    try {
                        withContext(Dispatchers.IO) {
                            addPlayerToDB(currentLoadout.loadoutPlayerName)
                            dataBase.loadoutDAO().insertLoadout(currentLoadout)
                        }
                        displayMessage(getApplication<Application>().getString(R.string.edit_loadout_success_insert))
                    }catch (e:Exception){
                        e.printStackTrace()
                        displayMessage(getApplication<Application>().getString(R.string.edit_loadout_error_insert))
                    }
                }
            }
            _loadout.value = currentLoadout
        }
        else
        {
            displayMessage(getApplication<Application>().getString(R.string.edit_loadout_error_validation))
        }
    }

    private fun addPlayerToDB(playerName:String)
    {
            val player: Player? = getPlayerWithLevelsAPI(playerName)
            if (player != null) {
                insertPlayer(player)
            } else {
                //We add the player without levels and tell the user we couldnt get the player's levels
                insertPlayer(Player(playerName = playerName, CombatLevels()))
            }
    }

    fun insertPlayer(addedPlayer:Player)
    {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        viewModelScope.launch {

                if (!withContext(Dispatchers.IO) {dataBase.playerDAO().isPlayerExists(addedPlayer.playerName)}) {
                    withContext(Dispatchers.IO) {dataBase.playerDAO().insertPlayer(addedPlayer)}
                    if(addedPlayer.combatLevels.combatLevel != 0) {
                        displayMessage(getApplication<Application>().getString(R.string.edit_loadout_yes_levels_insert))
                    }else{
                        displayMessage(getApplication<Application>().getString(R.string.edit_loadout_no_levels_insert))
                    }
                }else{
                    //Lvl 0 combat level is impossible so we know it was found
                    if(addedPlayer.combatLevels.combatLevel != 0) {
                        withContext(Dispatchers.IO) {dataBase.playerDAO().updatePlayer(addedPlayer)}
                    }
                }

        }
    }

    fun replaceLoadout()
    {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        val insertedLoadout:Loadout? = _loadout.value
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(insertedLoadout != null) {
                    addPlayerToDB(insertedLoadout.loadoutPlayerName)
                    dataBase.loadoutDAO().updateLoadout(insertedLoadout)
                }
            }
        }
        displayMessage(getApplication<Application>().getString(R.string.edit_loadout_success_replace))
    }

    private fun getPlayerWithLevels(playerName: String): Player? {
        var player:Player? = getPlayerWithLevelsAPI(playerName)
        if(player != null) {
            return player
        }
        player = getPlayerWithLevelsDB(playerName)
        if(player != null)
        {
            return player
        }
        //Couldnt find levels either online or in db
        return null
    }

    private fun getPlayerWithLevelsAPI(playerName: String):Player? {
        val client = HiScoreAPIClient.getInstance().create(HiScoreAPI::class.java)
        var playerReturned:Player? = null
        viewModelScope.launch(Dispatchers.IO) {
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
        }
        return playerReturned
    }

    private fun getPlayerWithLevelsDB(playerName: String):Player? {
        val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        var player:Player?= null
        viewModelScope.launch{
            if(withContext(Dispatchers.IO) {dataBase.playerDAO().isPlayerExists(playerName)}) {
                withContext(Dispatchers.IO) {player = dataBase.playerDAO().getPlayer(playerName)}
            }
        }
        return player
    }
}