package com.example.osrsdex.db

import androidx.room.Dao
import androidx.room.Query
@Dao
interface LoadoutDAO {
    @Query("SELECT * FROM Loadout")
    suspend fun getAll(): List<Loadout>

}