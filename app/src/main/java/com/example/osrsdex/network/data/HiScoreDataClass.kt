package com.example.example

import com.google.gson.annotations.SerializedName


data class HiScoreDataClass (

  @SerializedName("skills" ) var skills : Skills? = Skills()

)