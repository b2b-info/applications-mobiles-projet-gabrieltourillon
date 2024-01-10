package com.example.osrsdex.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.osrsdex.TAG
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player

@Dao
interface LoadoutDAO {
    @Query("SELECT * FROM Loadout")
    suspend fun getAll(): List<Loadout>

    @Query("SELECT * FROM Loadout WHERE loadoutPlayerName LIKE (:loadoutPlayerName)")
    suspend fun getAllWherePlayerName(loadoutPlayerName: String) : List<Loadout>

    @Insert(entity = Loadout::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLoadout(loadout: Loadout)

    @Update
    suspend fun updateLoadout(loadout: Loadout)

    @Query("SELECT EXISTS(SELECT * FROM Loadout WHERE loadoutName LIKE :loadoutName AND loadoutPlayerName LIKE :loadoutPlayerName LIMIT 1)")
    suspend fun isLoadoutExists(loadoutName: String, loadoutPlayerName: String) : Boolean

    //Check if player exists, if he does compare combat levels, if playerCombatLevels > dbPlayerCombatlevels we update, if he doesnt exist we insert him, after all that we insert loadout
    @Transaction
    suspend fun insertLoadoutWithPlayer(loadout: Loadout, player: Player):Boolean
    {
        if(loadout.loadoutPlayerName == player.playerName) {
            val existingPlayer: Player? = getPlayer(player.playerName)
            var insertedPlayer = player
            try {
                //If player doesn't exist we insert
                if(existingPlayer == null) {
                    insertPlayer(player)
                }else{
                    if ((existingPlayer.combatLevels.combatLevel != null) && (player.combatLevels.combatLevel != null)) {
                        if (player.combatLevels.combatLevel!! > existingPlayer.combatLevels.combatLevel!!) {
                            insertedPlayer = existingPlayer
                        }
                        updatePlayer(insertedPlayer)
                    }
                }
                insertLoadout(loadout)
                return true
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
        }
        return false
    }

    @Transaction
    suspend fun updateLoadoutWithPlayer(loadout: Loadout, player: Player):Boolean
    {
        if(loadout.loadoutPlayerName == player.playerName) {
            val existingPlayer: Player? = getPlayer(player.playerName)
            var insertedPlayer = player
            try {
                //If player doesn't exist we insert
                if(existingPlayer == null) {
                    insertPlayer(player)
                }else{
                    if ((existingPlayer.combatLevels.combatLevel != null) && (player.combatLevels.combatLevel != null)) {
                        if (player.combatLevels.combatLevel!! > existingPlayer.combatLevels.combatLevel!!) {
                            insertedPlayer = existingPlayer
                        }
                        updatePlayer(insertedPlayer)
                    }
                }
                updateLoadout(loadout)
                return true
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
        }
        return false
    }

    @Insert(entity = Player::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Update(entity = Player::class)
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM Player WHERE playerName = :playerName")
    suspend fun getPlayer(playerName: String):Player?
}