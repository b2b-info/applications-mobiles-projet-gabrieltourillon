package com.example.osrsdex.activities.loadout.viewloadouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.osrsdex.R
import com.example.osrsdex.db.AppDatabase
import com.example.osrsdex.models.Loadout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewLoadoutsViewModel(application: Application) : AndroidViewModel(application) {
    val loadoutList: MutableLiveData<List<Loadout>> = MutableLiveData()

    //UIState for displaying snackbars
    private val _uiState = MutableStateFlow(ViewLoadoutsUIState())
    val uiState: StateFlow<ViewLoadoutsUIState> = _uiState
    fun updateLoadoutList(playerName: String)
    {
        if (playerName.isBlank()) {
            //Blank Player Name
            displayMessage(getApplication<Application>().getString(R.string.view_loadouts_name_blank))
            return
        } else {
            //We get the loadouts who's playerName = etPlayerName.text
            viewModelScope.launch {
                try {
                    val dataBase = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
                    val isPlayer = withContext(Dispatchers.IO) { dataBase.playerDAO().isPlayerExists(playerName) }
                    if(isPlayer) {
                        val list = withContext(Dispatchers.IO) {
                            dataBase.loadoutDAO().getAllWherePlayerName(playerName)
                        }
                        if (list.isEmpty()) {
                            displayMessage(getApplication<Application>().getString(R.string.view_loadouts_list_empty))
                            return@launch
                        }
                        loadoutList.value = list
                    }
                    else
                    {
                        displayMessage(getApplication<Application>().getString(R.string.view_loadouts_no_player))
                        return@launch
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    displayMessage(getApplication<Application>().getString(R.string.view_loadouts_list_error))
                }
            }
        }
    }

    fun displayMessage(message:String)
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = message) }
    }

    fun messageShown()
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = null) }
    }
}