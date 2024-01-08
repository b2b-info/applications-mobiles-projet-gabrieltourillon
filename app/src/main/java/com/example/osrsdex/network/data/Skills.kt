package com.example.example

import com.google.gson.annotations.SerializedName


data class Skills (

  @SerializedName("overall"      ) var overall      : Overall?      = Overall(),
  @SerializedName("attack"       ) var attack       : Attack?       = Attack(),
  @SerializedName("defence"      ) var defence      : Defence?      = Defence(),
  @SerializedName("strength"     ) var strength     : Strength?     = Strength(),
  @SerializedName("hitpoints"    ) var hitpoints    : Hitpoints?    = Hitpoints(),
  @SerializedName("ranged"       ) var ranged       : Ranged?       = Ranged(),
  @SerializedName("prayer"       ) var prayer       : Prayer?       = Prayer(),
  @SerializedName("magic"        ) var magic        : Magic?        = Magic()
)