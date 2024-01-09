package com.example.osrsdex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.osrsdex.models.Loadout

@Dao
interface LoadoutDAO {
    @Query("SELECT * FROM Loadout")
    suspend fun getAll(): List<Loadout>

    @Query("SELECT * FROM Loadout WHERE loadoutPlayerName LIKE (:loadoutPlayerName)")
    suspend fun getAllWherePlayerName(loadoutPlayerName: String) : List<Loadout>

    @Insert(entity = Loadout::class)
    suspend fun insertLoadout(loadout: Loadout)

    @Update
    suspend fun updateLoadout(loadout: Loadout)

    @Query("SELECT EXISTS(SELECT * FROM Loadout WHERE loadoutName LIKE :loadoutName AND loadoutPlayerName LIKE :loadoutPlayerName LIMIT 1)")
    fun isLoadoutExists(loadoutName: String, loadoutPlayerName: String) : Boolean

}