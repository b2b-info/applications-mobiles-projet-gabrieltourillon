package com.example.osrsdex.activities.loadout.editloadout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player

class EditLoadoutsViewModel: ViewModel(){
    var player: MutableLiveData<Player> = MutableLiveData()
    var loadout: LiveData<Loadout> = MutableLiveData()
}