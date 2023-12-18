package com.example.osrsdex.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.osrsdex.models.AtkStyle
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["loadoutName", "player"])
data class Loadout(
    var loadoutName: String,
    var player: String,
    var description: String,
    var stats: Int,
    var atkStyle: String
) :Parcelable

@Parcelize
@Entity
data class Player(
    @PrimaryKey
    var playerName: String
):Parcelable



/**
 * Data class containing all combat stats.
 */
@Parcelize
data class CombatStats
    (
    ///Properties for stats///
    //Levels//
    var lvlHp : Int,
    var lvlAtk : Int,
    var lvlStr : Int,
    var lvlDef : Int,
    var lvlMag : Int,
    var lvlRng : Int,
    //AtkBonuses//
    var bnsStb : Int,
    var bnsSls : Int,
    var bnsCrs : Int,
    var bnsMag : Int,
    var bnsRng : Int,
    //StrBonuses//
    var bnsStrMel : Int,
    var bnsStrMag : Int,
    var bnsStrRng : Int,
    //DefBonuses//
    var defStb : Int,
    var defSls : Int,
    var defCrs : Int,
    var defMag : Int,
    var defRng : Int,
    ///Other Properties///
    //Attack speed in ticks(1 tick = .6s), used to calculate DPS//
    var atkSpeed: Int
) : Parcelable
