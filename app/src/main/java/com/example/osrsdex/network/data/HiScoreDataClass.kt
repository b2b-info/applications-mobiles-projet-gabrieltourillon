package com.example.example

import com.example.osrsdex.models.CombatLevels
import com.google.gson.annotations.SerializedName


data class HiScoreDataClass (

  @SerializedName("skills" ) var skills : Skills? = Skills()

)