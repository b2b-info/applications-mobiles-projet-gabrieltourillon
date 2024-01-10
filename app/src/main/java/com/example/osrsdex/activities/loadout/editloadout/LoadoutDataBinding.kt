package com.example.osrsdex.activities.loadout.editloadout

import com.example.osrsdex.models.CombatBonuses
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player

/**
 * Contains the representation of the data the view EditLoadout contains.
 * View fills it up by sending a Map.
 * This class has validation for the strings and can convert it's dataMap into a Loadout object with createLoadout():Loadout
 * Not actually data binding because we didn't learn how and I don't have time to learn it
 *
 * @property intDataMap Map containing the strings we validate then convert into ints
 * @property stringDataMap Map containing the strings we validate then keep as strings
 */
data class LoadoutDataBinding(
    val intDataMap:Map<String,String>,
    val stringDataMap:Map<String,String>,
)
{
    private var _combatBonuses:CombatBonuses? = null
    private lateinit var _loadoutPlayerName:String
    private lateinit var _loadoutName:String
    private var _loadoutDescription:String? = null
    fun createLoadout(): Loadout
    {
        return Loadout(
            loadoutName = _loadoutName,
            loadoutPlayerName = _loadoutPlayerName,
            description = _loadoutDescription,
            combatBonuses = _combatBonuses
        )
    }

    fun validateData():Boolean
    {
        return(validateIntData()&&validateStringData())
    }

    fun validateStringData():Boolean
    {
        try {
            _loadoutPlayerName = stringDataMap["playerName"].toString()
            _loadoutName = stringDataMap["loadoutName"].toString()
            _loadoutDescription = stringDataMap["loadoutDescription"].toString()
            return true
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
        return false
    }


    fun validateIntData(): Boolean
    {
        try {
            //TODO("Not implemented yet")
            val atkSpeed = 0
            val bnsPry = 0

            val mapStats = mutableMapOf<String, Int>()

            for (entry in intDataMap.entries.iterator()) {
                //If the EditText is not blank
                if (entry.value.isNotBlank() && entry.value.isNotEmpty()) {
                    mapStats[entry.key] = entry.value.toInt()
                } else {
                    mapStats[entry.key] = 0
                }
            }

            ///Give value
            _combatBonuses = CombatBonuses(
                mapStats["bnsStb"],
                mapStats["bnsSls"],
                mapStats["bnsCrs"],
                mapStats["bnsMag"],
                mapStats["bnsRng"],
                mapStats["bnsStrMel"],
                mapStats["bnsStrMag"],
                mapStats["bnsStrRng"],
                mapStats["defStb"],
                mapStats["defSls"],
                mapStats["defCrs"],
                mapStats["defMag"],
                mapStats["defRng"],
                atkSpeed,
                bnsPry
            )
            return true;
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
        return false
    }
}

