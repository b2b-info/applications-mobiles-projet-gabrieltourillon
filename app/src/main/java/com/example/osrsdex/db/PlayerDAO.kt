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
    suspend fun insertPlayer(player:Player)

    @Update
    suspend fun updatePlayer(player:Player)

    @Query("SELECT EXISTS(SELECT * FROM Player WHERE playerName LIKE :playerName LIMIT 1)")
    fun isPlayerExists(playerName: String) : Boolean

    @Query("SELECT * FROM Player " +
           "JOIN Loadout ON Player.playerName = Loadout.LoadoutName")
    fun getPlayerWithLoadouts(): Map<Player, List<Loadout>>
}