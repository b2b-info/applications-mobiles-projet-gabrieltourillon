package com.example.osrsdex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player
import retrofit2.http.GET

@Dao
interface PlayerDAO {
    @Insert(entity = Player::class)
    suspend fun insertPlayer(player:Player)

    @Update
    suspend fun updatePlayer(player:Player)

    @Query("SELECT EXISTS(SELECT * FROM Player WHERE playerName LIKE :playerName LIMIT 1)")
    suspend fun isPlayerExists(playerName: String) : Boolean

    @Query("SELECT * FROM Player WHERE playerName = :playerName")
    suspend fun getPlayer(playerName: String):Player?

    @Query("SELECT * FROM Player " +
           "JOIN Loadout ON Player.playerName = Loadout.LoadoutName")
    suspend fun getPlayerWithLoadouts(): Map<Player, List<Loadout>>
}