package com.example.osrsdex.db

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
) :Parcelable


/**
 * Data class containing all combat stats.
 */
@Parcelize
@Entity
data class CombatStats
    (
    ///Properties for stats///
    //Levels//
    var lvlHp : Int?,
    var lvlAtk : Int?,
    var lvlStr : Int?,
    var lvlDef : Int?,
    var lvlMag : Int?,
    var lvlRng : Int?,
    //AtkBonuses//
    var bnsStb : Int?,
    var bnsSls : Int?,
    var bnsCrs : Int?,
    var bnsMag : Int?,
    var bnsRng : Int?,
    //StrBonuses//
    var bnsStrMel : Int?,
    var bnsStrMag : Int?,
    var bnsStrRng : Int?,
    //DefBonuses//
    var defStb : Int?,
    var defSls : Int?,
    var defCrs : Int?,
    var defMag : Int?,
    var defRng : Int?,
    ///Other Properties///
    //Attack speed in ticks(1 tick = .6s), used to calculate DPS//
    var atkSpeed: Int?,
    //Prayer bonus//
    var bnsPry: Int?
) : Parcelable
