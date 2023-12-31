package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["loadoutName", "playerName"])
data class Loadout(
    var loadoutName: String,
    var playerName: String,
    var description: String?,
    @Embedded var stats: CombatStats?,
    var atkStyle: String?
) : Parcelable