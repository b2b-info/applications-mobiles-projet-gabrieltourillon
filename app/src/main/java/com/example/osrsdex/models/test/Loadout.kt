package com.example.osrsdex.models.test

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import com.example.osrsdex.models.CombatStats
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["entityName", "playerName"])
data class Loadout(
    override var entityName: String,
    var playerName: String,
    var description: String?,
    @Embedded
    override var stats: CombatStats?,
    override var atkStyle: String?
) : IEntity, Parcelable