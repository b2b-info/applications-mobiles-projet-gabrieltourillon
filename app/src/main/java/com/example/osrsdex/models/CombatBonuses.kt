package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Data class containing all combat bonuses.
 */
@Parcelize
data class CombatBonuses
    (
    ///Properties for stats///
    //AtkBonuses//
    var bnsStb: Int? = 0,
    var bnsSls: Int? = 0,
    var bnsCrs: Int? = 0,
    var bnsMag: Int? = 0,
    var bnsRng: Int? = 0,
    //StrBonuses//
    var bnsStrMel: Int? = 0,
    var bnsStrMag: Int? = 0,
    var bnsStrRng: Int? = 0,
    //DefBonuses//
    var defStb: Int? = 0,
    var defSls: Int? = 0,
    var defCrs: Int? = 0,
    var defMag: Int? = 0,
    var defRng: Int? = 0,
    ///Other Properties///
    //Attack speed in ticks(1 tick = .6s), used to calculate DPS//
    var atkSpeed: Int? = 0,
    //Prayer bonus//
    var bnsPry: Int? = 0,
) : Parcelable
