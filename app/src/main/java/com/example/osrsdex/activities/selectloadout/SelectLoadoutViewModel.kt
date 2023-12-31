package com.example.osrsdex.activities.selectloadout

import androidx.lifecycle.ViewModel
import com.example.osrsdex.models.Loadout

class SelectLoadoutViewModel() : ViewModel() {
    val loadoutList: MutableList<Loadout> = mutableListOf()
}