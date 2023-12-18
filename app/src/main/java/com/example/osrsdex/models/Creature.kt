package com.example.osrsdex.models

/**
 * Enum that contains all possible AtkStyles:
 * STAB, SLASH, CRUSH, MAGIC, RANGED
 */
enum class AtkStyle {
    STAB, SLASH, CRUSH, MAGIC, RANGED
}


/**
 * Any living entity in osrs.
 * Has stats and it's calculating functions.
 */
open class Creature {
    var stats : CombatStats? = null
    lateinit var atkStyle: AtkStyle
}
