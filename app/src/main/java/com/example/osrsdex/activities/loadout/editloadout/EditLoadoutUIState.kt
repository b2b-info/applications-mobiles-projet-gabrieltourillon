package com.example.osrsdex.activities.loadout.editloadout

import com.google.android.material.snackbar.Snackbar

data class EditLoadoutUIState(
    val userMessage:String? = null,
    val snackbarLenght: Int = Snackbar.LENGTH_LONG,
    val isNeedShowAlert: Boolean? = null,
    val isPlayerLevelsReady: Boolean = false
)
