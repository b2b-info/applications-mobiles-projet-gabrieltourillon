package com.example.osrsdex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player

@Dao
interface PlayerDAO {
    @Insert(entity = Player::class)
    suspend fun insertLoadout(player:Player)

    @Update
    suspend fun updateLoadout(player:Player)

    @Query("SELECT * FROM Player " +
           "JOIN Loadout ON Player.playerName = Loadout.LoadoutName")
    fun getPlayerWithLoadouts(): Map<Player, List<Loadout>>
}