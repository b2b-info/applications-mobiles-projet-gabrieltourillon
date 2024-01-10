package com.example.osrsdex.activities.loadout.editloadout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.HiScoreDataClass
import com.example.osrsdex.R
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
            var player: Player? = getPlayerWithLevelsAPI(loadout.loadoutPlayerName)
            if (player == null)
            {
                //If we couldnt get the levels we just make a player without levels
                player = Player(playerName = loadout.loadoutPlayerName, CombatLevels())
            }
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
        var player: Player? = getPlayerWithLevelsAPI(loadout.loadoutPlayerName)
        if (player == null) {
            //If we couldnt get the levels we just make a player without levels and tell the user
            player = Player(playerName = loadout.loadoutPlayerName, CombatLevels())
            //TODO('TELL USER COULDNT GET LEVELS')
        }

        if (dataBase.loadoutDAO().updateLoadoutWithPlayer(loadout, player)) {
            displayMessage(getApplication<Application>().getString(R.string.edit_loadout_success_replace))
        }
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
}