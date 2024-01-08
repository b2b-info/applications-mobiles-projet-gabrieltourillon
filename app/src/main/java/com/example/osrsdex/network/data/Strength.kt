package com.example.example

import com.google.gson.annotations.SerializedName


data class Strength (

  @SerializedName("rank"  ) var rank  : Int? = null,
  @SerializedName("level" ) var level : Int? = null,
  @SerializedName("xp"    ) var xp    : Int? = null

)