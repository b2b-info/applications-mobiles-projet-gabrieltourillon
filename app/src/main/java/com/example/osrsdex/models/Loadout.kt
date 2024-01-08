package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["loadoutName", "loadoutPlayerName"])
data class Loadout(
    var loadoutName: String,
    var loadoutPlayerName: String,
    var description: String?,
    @Embedded var stats: CombatBonuses?
) : Parcelable
{
    @IgnoredOnParcel
    @Ignore var atkStyle: String? = null
}