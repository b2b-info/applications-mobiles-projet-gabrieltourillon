package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import kotlin.math.floor

/**
 * Data class containing all combat combat levels.
 * Used by players and monsters.
 *
 */
@Parcelize
data class CombatLevels
    (
    //Levels//
    var combatLevel: Int? = 0,
    val lvlHp : Int? = 0,
    val lvlAtk : Int? = 0,
    val lvlStr : Int? = 0,
    val lvlDef : Int? = 0,
    val lvlMag : Int? = 0,
    val lvlRng : Int? = 0,
    val lvlPry : Int? = 0
) : Parcelable
{
    init
    {
        combatLevel = calcCombatLevel()
    }

    private fun calcCombatLevel():Int
    {
        //Base combat level, added to highest of melee/range/mage to make combat level
        val base:Double = (lvlDef!!.toDouble() + lvlHp!!.toDouble() + floor(lvlPry!!.toDouble()/2))/4
        //Combat level of melee levels without base combat level
        val melee:Double = (lvlAtk!!.toDouble() + lvlStr!!.toDouble()) * 13/40
        //Combat level of range levels without base combat level
        val range:Double = floor(lvlRng!!.toDouble() * 3/2) * 13/40
        //Combat level of mage levels without base combat level
        val mage:Double = floor(lvlMag!!.toDouble() * 3/2) * 13/40

        //Return combat level
        return floor(base + maxOf(melee,range,mage)).toInt()

    }
}

