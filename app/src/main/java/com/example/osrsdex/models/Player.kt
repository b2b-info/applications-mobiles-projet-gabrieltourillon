package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Player (
    @PrimaryKey
    val playerName: String,
    @Embedded val combatLevels: CombatLevels
):Parcelable