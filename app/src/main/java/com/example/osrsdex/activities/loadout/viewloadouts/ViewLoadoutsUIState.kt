package com.example.osrsdex.activities.loadout.viewloadouts

import com.google.android.material.snackbar.Snackbar

data class ViewLoadoutsUIState(
    val userMessage:String? = null,
    val snackbarLenght: Int = Snackbar.LENGTH_LONG
)