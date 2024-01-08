package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["monsterName", "combatLevels.combatLevel"])
data class Monster(
    val monsterName: String,
    val combatLevels: CombatLevels,
    val combatBonuses: CombatBonuses
):Parcelable
