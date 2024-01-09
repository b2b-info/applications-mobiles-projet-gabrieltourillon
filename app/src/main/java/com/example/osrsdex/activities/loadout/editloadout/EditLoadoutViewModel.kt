package com.example.osrsdex.activities.loadout.editloadout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EditLoadoutViewModel: ViewModel(){
    var player: MutableLiveData<Player> = MutableLiveData()
    var loadout: LiveData<Loadout> = MutableLiveData()

    //UIState for displaying snackbars
    private val _uiState = MutableStateFlow(EditLoadoutUIState())
    val uiState: StateFlow<EditLoadoutUIState> = _uiState



    fun displayMessage(message:String)
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = message) }
    }

    fun messageShown()
    {
        _uiState.update { currentUiState -> currentUiState.copy(userMessage = null) }
    }
}