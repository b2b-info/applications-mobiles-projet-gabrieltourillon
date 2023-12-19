package com.example.osrsdex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LoadoutDAO {
    @Query("SELECT * FROM Loadout")
    suspend fun getAll(): List<Loadout>

    @Query("SELECT * FROM Loadout WHERE playerName LIKE (:pn)")
    suspend fun getAllWherePlayerName(pn: String) : List<Loadout>

    @Insert(entity = Loadout::class)
    suspend fun insertLoadout(loadout: Loadout)

    @Update
    suspend fun updateLoadout(loadout: Loadout)

    @Query("SELECT EXISTS(SELECT * FROM Loadout WHERE loadoutName LIKE :ln AND playerName LIKE :pn LIMIT 1)")
    fun isLoadoutExists(pn: String, ln: String) : Boolean

}